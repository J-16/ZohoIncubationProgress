package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.controllers.PaymentController;
import com.company.subscriptionmanagement.controllers.PaymentMethodController;
import com.company.subscriptionmanagement.database.*;
import com.company.subscriptionmanagement.database.DataStructures.NotificationDS;
import com.company.subscriptionmanagement.exception.*;
import com.company.subscriptionmanagement.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SubscriberService{

    private Subscriber subscriber = null;
    private String email;
    private String name;
    private Company company;
    private NotificationService notificationService;
    private UserDB database; // TODO
    private PaymentController paymentController;

    private ProductsDB productsDB;
    private SubscriptionPlanDB subscriptionPlanDB;
    private CouponDB couponDB;
    private NewsLetterSubscribersDB newsLetterSubscribersDB;
    private TrailSubscribersDB trailSubscribersDB;
    private ProductSubscribersDB productSubscribersDB;
    private AutoRenewalDB autoRenewalDB;
    private IssueDB issueDB;
    NotificationDB notificationDB;

    public SubscriberService(String email, String name, Company company){
        this.email = email;
        this.name = name;
        this.company = company;

        this.database = CurrentDatabase.getUserDatabase();
        this.productsDB = CurrentDatabase.getProductsDB();
        this.subscriptionPlanDB = CurrentDatabase.getSubscriptionPlanDBB();
        this.couponDB = CurrentDatabase.getCouponDB();
        this.newsLetterSubscribersDB = CurrentDatabase.getNewsLetterSubscribersDB();
        this.trailSubscribersDB = CurrentDatabase.getTrailSubscribersDB();
        this.productSubscribersDB = CurrentDatabase.getProductSubscribersDB();
        this.autoRenewalDB = CurrentDatabase.getAutoRenewalDB();
        this.issueDB = CurrentDatabase.getIssueDB();
        this.notificationDB = CurrentDatabase.getNotificationDB();
        this.subscriber = getSubscriber();
    }

    public void activateTrail(String productName) {
        Product product = getProductByCompany(productName);
        if(isSubscribed(product))
            throw new InvalidOperationException("You have subscribed to the product already");
        if(trailSubscribersDB.getByCompanyID(company.getID(),product.getID(),subscriber.getID()) != null){
            throw new InvalidOperationException("You have already enabled trail version");
        }
        trailSubscribersDB.save(new TrailVersion(company.getID(), subscriber.getID(), product.getID(), LocalDate.now().plusDays(product.getTrailDays())));
    }

    public void subscribeProduct(String productName, Long planID, String couponName){
        Product product  = getProductByCompany(productName);
        if(isSubscribed(product))
            throw new InvalidOperationException("You have subscribed to the product already");
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByProduct(product, planID);
        TrailVersion trailVersion = trailSubscribersDB.getByID(subscriber.getID());
        if( trailVersion != null ){
            trailVersion.setOver(true);
            trailSubscribersDB.update(trailVersion);
        }
        double actualPrice = subscriptionPlan.getPrice();
        double discountPrice = actualPrice;
        if(couponName != null){
            Coupon coupon = getCoupon(product, couponName);
            discountPrice = actualPrice - actualPrice * (coupon.getDiscount()/100);
        }
        processPayment(actualPrice,discountPrice);
        CurrentSubscription currentSubscription = new CurrentSubscription(subscriber.getID(), company.getID(), subscriptionPlan);
        productSubscribersDB.save(currentSubscription);
        setAutoRenewal(currentSubscription);
    }

    public void changeSubscription(String productName, long subscriptionPlanID){
        Product product = getProductByCompany(productName);
        checkUpgrade(product, subscriptionPlanID);
        CurrentSubscription currentSubscription = productSubscribersDB.getByID(company.getID(), subscriber.getID());
        cancelAutoRenewal(currentSubscription);
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlanID);
        double price  = newSubscriptionPlan.getPrice();
        processPayment(price,price);
        currentSubscription.setSubscriptionPlanId(newSubscriptionPlan.getID());
        productSubscribersDB.update(currentSubscription);
        setAutoRenewal(productSubscribersDB.getByID(company.getID(), subscriber.getID()));
    }

    private void processPayment(double actualPrice, double discountPrice){
        subscriber = getSubscriber();
        paymentController = new PaymentController(actualPrice,subscriber);
        new PaymentMethodController(actualPrice, discountPrice ,subscriber, paymentController).getPaymentMethod();
        paymentController.processPayment();
    }

    public void pauseSubscription(String productName, LocalDate resumeDate){
        Product product = getProductByCompany(productName);
        if(!isSubscribed(product))
            throw new InvalidOperationException("You have not subscribed to perform this operation");
        if(resumeDate.getDayOfMonth() < LocalDate.now().getDayOfMonth() && resumeDate.getMonthValue() < LocalDate.now().getMonthValue() && resumeDate.getYear() < LocalDate.now().getYear())
            throw new InputException("Invalid date", InputException.ExceptionType.INVALID_FORMAT, "resumeDate");
        CurrentSubscription currentSubscription = productSubscribersDB.getByID(company.getID(), subscriber.getID());
        if(currentSubscription == null)
            throw new InvalidOperationException("You have not subscribed to perform this operation");
        cancelAutoRenewal(currentSubscription);
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setPausedDate(LocalDate.now(),getSubscriptionPlan(product,currentSubscription.getSubscriptionPlanId()));
        currentSubscription.setResumeSubscriptionDate(resumeDate);
        setAutoRenewal(currentSubscription);
    }

    public void cancelSubscription(String productName){
        Product product = getProductByCompany(productName);
        if(!isSubscribed(product)){
            throw new InvalidOperationException("You have not subscribed to perform this operation");
        }
        CurrentSubscription currentSubscription = productSubscribersDB.getByID(company.getID(), subscriber.getID());
        cancelAutoRenewal(currentSubscription);
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setCancelledDate(LocalDate.now());
        currentSubscription.setResumeSubscriptionDate(null);
    }

    public void subscribeNewsLetter(String productName){
        try {
            Product product = getProductByCompany(productName);
            newsLetterSubscribersDB.save(new NewsLetter(company.getID(),subscriber.getID(), product.getID()));
        }catch(DatabaseException e){}
    }

    public void cancelNewsLetterSubscription(String productName){
        try {
            Product product = getProductByCompany(productName);
            newsLetterSubscribersDB.delete(subscriber.getID(), company.getID(), product.getID());
        }catch(DatabaseException e){};
    }

    public SubscriptionPlan getSubscriptionPlan(Product product, long SubscriptionPlanID){
        for(SubscriptionPlan subscriptionPlan : subscriptionPlanDB.getByCompanyID(company.getID())){
            if(subscriptionPlan.getID() == SubscriptionPlanID){
                return subscriptionPlan;
            }
        }
        throw new DatabaseException("Invalid subscription name", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
    }

    public Product getProductByCompany(String productName){
        for(Product product : productsDB.getProductsByCompanyID(company.getID())){
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        throw new DatabaseException("No such product name found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "productName");
    }

    public ArrayList<String> getProductsByCompany() {
        ArrayList<String> products = new ArrayList<>();
        for(Product product : productsDB.getProductsByCompanyID(company.getID())){
            products.add(product.getProductName());
        }
        if(products.size() == 0)
            throw new DatabaseException("No Products available at the moment", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return products;
    }

    public void setAutoRenewal(CurrentSubscription currentSubscription){
        HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal = autoRenewalDB.getAutoRenewal();
        if(autoRenewal.containsKey(currentSubscription.getExpireDate())){
            autoRenewalDB.update(currentSubscription);
            return;
        }
        autoRenewalDB.save(currentSubscription);
    }

    private void cancelAutoRenewal(CurrentSubscription currentSubscription){
        autoRenewalDB.delete(currentSubscription);
    }

    public ArrayList<SubscriptionPlan> getAllSubscriptionPlanByCompany(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlan = null;
        for(Product product : productsDB.getProductsByCompanyID(company.getID())){
            if(product.getProductName().equals(productName)){
                subscriptionPlan = subscriptionPlanDB.getByProductID(company.getID(), product.getID());
                break;
            }
        }
        if(subscriptionPlan == null || subscriptionPlan.size() == 0)
            throw new DatabaseException("No subscription plans are available for this product at the moment", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return subscriptionPlan;
    }

    public HashMap<String,CurrentSubscription> getSubscriptionBySubscriber(){
        HashMap<String,CurrentSubscription> subscriptions = new HashMap<>();
        for(Product product : productsDB.getProductsByCompanyID(company.getID())){
            CurrentSubscription currentSubscription = productSubscribersDB.getByID(company.getID(), subscriber.getID(), product.getID());
            if(currentSubscription != null){
                subscriptions.put(product.getProductName(), currentSubscription);
            }
        }
        if(subscriptions.size() == 0)
            throw new DatabaseException("You have not subscribed to any products yet", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return subscriptions;
    }

    public ArrayList<String> getSubscribedNewsletter() {
        ArrayList<String> newsletter = new ArrayList<>();
        for(Product product : productsDB.getProductsByCompanyID(company.getID())){
            if( newsLetterSubscribersDB.getByProductID(company.getID(),product.getID(),subscriber.getID()) ){
                newsletter.add(product.getProductName());
            }
        }
        if(newsletter.size() == 0)
            throw new InvalidOperationException("You have not subscribed to any newsletters so far");
        return newsletter;
    }

    public ArrayList<String> getNotification(){
        subscriber = getSubscriber();
        if(subscriber == null){
            throw new DatabaseException("You are not a subscriber to receive notification", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
        ArrayList<String> notification  = notificationDB.getBySubscriberID(subscriber.getID());
        if(notification == null || notification.size() == 0)
            throw new DatabaseException("No notification so far", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return notification;
    }

    private Subscriber getSubscriber(){
        subscriber = database.getSubscribersByEmail(email);
        if( subscriber == null ){
            database.registerSubscriber(email, name);
            subscriber = database.getSubscribersByEmail(email);
        }
        return subscriber;
    }


    private Coupon getCoupon(Product product, String couponName){
        if(couponDB.getCoupons().size() == 0)
            throw new DatabaseException("Invalid coupon", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "couponName");
        LocalDate date = LocalDate.now();
        for(Coupon coupon : couponDB.getCoupons()){
            if(coupon.getCouponName().equals(couponName) && date.isBefore(coupon.getExpiryDate()))
                return coupon;
        }
        throw new DatabaseException("Invalid coupon name", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "couponName");
    }

    private boolean isSubscribed(Product product){
        CurrentSubscription currentSubscription = productSubscribersDB.getByID(company.getID(), subscriber.getID());
        if(currentSubscription == null) return false;
        return currentSubscription.isCurrentlySubscribed();
    }

    private void checkUpgrade(Product product, long subscriptionPlanID){
        SubscriptionPlan oldSubscriptionPlan = getSubscriptionPlan(product, productSubscribersDB.getByID(company.getID(), subscriber.getID()).getSubscriptionPlanId());
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlanID);
        if(oldSubscriptionPlan.getPrice() < newSubscriptionPlan.getPrice())
            throw new InvalidOperationException("Illegal operation");
    }

    private SubscriptionPlan getSubscriptionPlanByProduct(Product product, long planID) {
        for(SubscriptionPlan subscriptionPlan : subscriptionPlanDB.getByCompanyID(company.getID())){
            if(subscriptionPlan.getID() == planID){
                return subscriptionPlan;
            }
        }
        throw new DatabaseException("No such subscription plan", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
    }

    public HashMap<String, LocalDate> getTrailSubscribedProducts() {
        HashMap<String, LocalDate> trail = new HashMap<>();
        for(Product product : productsDB.getProductsByCompanyID(company.getID())){
            LocalDate date = LocalDate.now();
            LocalDate expiryDate = null;
            TrailVersion trailVersion = trailSubscribersDB.getByCompanyID(company.getID(),product.getID(),subscriber.getID());
            if(trailVersion != null)
                expiryDate = trailVersion.getExpiryDate();
            if( expiryDate != null && date.isBefore(expiryDate))
                trail.put(product.getProductName(),expiryDate);
        }
        if(trail.size() == 0)
            return null;
        return trail;
    }

    public void giftSubscription(String productName, Long planName, String coupon, String email) {
        //TODO: // check mail with the company and mail user with the subscription plan
        // Handle payment new PaymentController().processPayment(price,subscriber);
        //notificationService.send();
    }

    public void raiseIssue(String message){
        issueDB.save(new Issue(email,message,company.getID()));
    }

}
