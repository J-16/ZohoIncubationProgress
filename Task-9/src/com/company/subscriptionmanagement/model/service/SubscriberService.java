package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.controllers.PaymentController;
import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.*;
import com.company.subscriptionmanagement.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SubscriberService {

    private Subscriber subscriber;
    private String email;
    private String name;
    private Company company;
    private NotificationService notificationService;

    public SubscriberService(String email, String name, Company company){
        this.email = email;
        this.name = name;
        this.company = company;
    }

    public void activateTrail(String productName) {
        Product product = getProductByCompany(productName);
        if(isSubscribed(productName))
            throw new InvalidOperationException("You have subscribed to the product already");
        if(product.getTrailSubscribers(email) != null){
            throw new InvalidOperationException("You have already enabled trail version");
        }
        subscriber = registerSubscriber();
        product.addTrailSubscribers(email, LocalDate.now().plusDays(30));
    }

    public void subscribeProduct(String productName, String planName, String couponName){
        if(isSubscribed(productName))
            throw new InvalidOperationException("You have subscribed to the product already");
        Product product  = getProductByCompany(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByProduct(product, planName);
        if(product.getTrailSubscribers(email) != null){
           product.setTrailSubscribers(email, LocalDate.now().minusDays(1));
        }
        double price = subscriptionPlan.getPrice();
        if(couponName != null){
            Coupon coupon = getCoupon(product, couponName);
            price = price + (coupon.getDiscount()/100);
        }
        Subscriber subscriber = registerSubscriber();
        new PaymentController().processPayment(price,subscriber);
        CurrentSubscription currentSubscription = new CurrentSubscription(subscriber,subscriptionPlan, subscriber.getPaymentDetails());
        product.addProductSubscribers(subscriber.getAccount().getEmail(), currentSubscription);
        setAutoRenewal(currentSubscription);
    }

    public void changeSubscription(String productName, String subscriptionPlan){
        Product product = getProductByCompany(productName);
        checkUpgrade(product, subscriptionPlan);
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlan);
        product.getProductSubscribers(subscriber.getAccount().getEmail()).setSubscriptionPlan(newSubscriptionPlan);
        new PaymentController().processPayment(newSubscriptionPlan.getPrice(),subscriber);
        setAutoRenewal(product.getProductSubscribers(email));
    }

    public void pauseSubscription(String productName, LocalDate resumeDate){
        if(!isSubscribed(productName))
            throw new InvalidOperationException("You have not subscribed to this product to pause");
        if(resumeDate.getDayOfMonth() < LocalDate.now().getDayOfMonth() && resumeDate.getMonthValue() < LocalDate.now().getMonthValue() && resumeDate.getYear() < LocalDate.now().getYear())
            throw new InputException("Invalid date", InputException.ExceptionType.INVALID_FORMAT, "resumeDate");
        Product product = getProductByCompany(productName);
        if(product.getProductSubscribers(email) == null)
            throw new InvalidOperationException("You have not subscribed to perform this operation");
        CurrentSubscription currentSubscription = product.getProductSubscribers(email);
        cancelAutoRenewal(currentSubscription);
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setPausedDate(LocalDate.now());
        currentSubscription.setResumeSubscriptionDate(resumeDate);
        setAutoRenewal(currentSubscription);
    }

    public void cancelSubscription(String productName){
        if(!isSubscribed(productName)) {
            throw new InvalidOperationException("You have not subscribed to perform this operation");
        }
        Product product = getProductByCompany(productName);
        CurrentSubscription currentSubscription = product.getProductSubscribers(email);
        cancelAutoRenewal(currentSubscription);
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setCancelledDate(LocalDate.now());
        currentSubscription.setResumeSubscriptionDate(null);
    }

    public void subscribeNewsLetter(String subscriberEmail, String productName){
        Product product = getProductByCompany(productName);
        product.setNewsLetterSubscribedUsers(subscriberEmail, true);
    }

    public void cancelNewsLetterSubscription(String subscriberEmail, String productName){
        Product product = getProductByCompany(productName);
        product.setNewsLetterSubscribedUsers(subscriberEmail, false);
    }

    public SubscriptionPlan getSubscriptionPlan(Product product, String newSubscriptionPlan){
        for(SubscriptionPlan subscriptionPlan : product.getSubscriptionPlan()){
            if(subscriptionPlan.getPlanName().equals(newSubscriptionPlan)){
                return subscriptionPlan;
            }
        }
        throw new DatabaseException("Invalid subscription name", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
    }

    public Product getProductByCompany(String productName){
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        throw new DatabaseException("No such product", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
    }

    public ArrayList<String> getProductsByCompany() {
        ArrayList<String> products = new ArrayList<>();
        for(Product product : company.getProducts()){
            products.add(product.getProductName());
        }
        if(products.size() == 0)
            throw new DatabaseException("No Products available at the moment", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return products;
    }

    public void setAutoRenewal(CurrentSubscription currentSubscription){
        HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal = company.getAutoRenewal();
        LinkedList<CurrentSubscription> currentSubscriptions;
        if(autoRenewal.containsKey(currentSubscription.getExpireDate())){
            currentSubscriptions = autoRenewal.get(currentSubscription.getExpireDate());
            currentSubscriptions.add(currentSubscription);
            autoRenewal.put(currentSubscription.getExpireDate(), currentSubscriptions);
            company.setAutoRenewal(autoRenewal);
            return;
        }
        currentSubscriptions = new LinkedList<>();
        currentSubscriptions.add(currentSubscription);
        autoRenewal.put(currentSubscription.getExpireDate(), currentSubscriptions);
        company.setAutoRenewal(autoRenewal);
    }

    private void cancelAutoRenewal(CurrentSubscription currentSubscription){
        HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal = company.getAutoRenewal();
        LinkedList<CurrentSubscription> currentSubscriptions = autoRenewal.get(currentSubscription.getExpireDate());
        for(int i =0; i< currentSubscriptions.size(); i++){
            System.out.println( currentSubscriptions.get(i).getSubscriber().getAccount().getEmail() );
            if(currentSubscriptions.get(i).getSubscriber().getAccount().getEmail().equals(currentSubscription.getSubscriber().getAccount().getEmail())) {
                currentSubscriptions.removeFirstOccurrence(i);
            }
        }
        autoRenewal.put(currentSubscription.getExpireDate(), currentSubscriptions);
        company.setAutoRenewal(autoRenewal);
    }

    public ArrayList<SubscriptionPlan> getAllSubscriptionPlanByCompany(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlan = null;
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                subscriptionPlan = product.getSubscriptionPlan();
                break;
            }
        }
        if(subscriptionPlan == null)
            throw new DatabaseException("No subscription plans are available for this product at the moment", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return subscriptionPlan;
    }

    public HashMap<String,CurrentSubscription> getSubscriptionBySubscriber(){
        HashMap<String,CurrentSubscription> subscriptions = new HashMap<>();
        for(Product product : company.getProducts()){
            if(product.getProductSubscribers(email) != null){
                subscriptions.put(product.getProductName(), product.getProductSubscribers(email));
            }
        }
        if(subscriptions.size() == 0)
            throw new DatabaseException("You have not subscribed to any products yet", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return subscriptions;
    }

    public ArrayList<String> getSubscribedNewsletter() {
        ArrayList<String> newsletter = new ArrayList<String>();
        for(Product product : company.getProducts()){
            if(product.getNewsLetterSubscribers().containsKey(email))
                newsletter.add(product.getProductName());
        }
        if(newsletter.size() == 0)
            throw new InvalidOperationException("No newsletter subscriptions so far");
        return newsletter;
    }

    public ArrayList<String> getNotification(){
        ArrayList<String> notification = subscriber.getNotification();
        if(notification == null)
            throw new DatabaseException("No notification", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return notification;
    }

    private Subscriber registerSubscriber() {
        Subscriber subscriber;
        subscriber = Database.getSubscribersByEmail(email);
        if( subscriber == null ){
            Database.registerSubscriber(email, name);
            subscriber = Database.getSubscribersByEmail(email);
        }
        return subscriber;
    }

    private Coupon getCoupon(Product product, String couponName) {
        if(product.getCoupons().size() == 0)
            throw new DatabaseException("Invalid coupon", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        LocalDate date = LocalDate.now();
        for(Coupon coupon : product.getCoupons()){
            if(coupon.getCouponName().equals(couponName) && date.getDayOfMonth() <= coupon.getExpiryDate().getDayOfMonth()
                    && date.getMonthValue() <= coupon.getExpiryDate().getMonthValue() && date.getYear() <= coupon.getExpiryDate().getYear())
                return coupon;
        }
        throw new DatabaseException("Invalid coupon", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
    }

    private boolean isSubscribed(String productName){
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                if(product.getProductSubscribers(email) != null && product.getProductSubscribers(email).isCurrentlySubscribed())
                    return true;
            }
        }
        return false;
    }

    private void checkUpgrade(Product product, String subscriptionPlan){
        SubscriptionPlan oldSubscriptionPlan = getSubscriptionPlan(product, product.getProductSubscribers(email).getSubscriptionPlan().getPlanName());
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlan);
        if(oldSubscriptionPlan.getPrice() < newSubscriptionPlan.getPrice())
            throw new InvalidOperationException("Your request will be processed soon");
    }

    private SubscriptionPlan getSubscriptionPlanByProduct(Product product, String planName) {
        for(SubscriptionPlan subscriptionPlan : product.getSubscriptionPlan()){
            if(subscriptionPlan.getPlanName().equals(planName)){
                return subscriptionPlan;
            }
        }
        throw new DatabaseException("No such subscription plan", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
    }

    public HashMap<String, LocalDate> getTrailSubscribedProducts() {
        HashMap<String, LocalDate> trail = new HashMap<>();
        for(Product product : company.getProducts()){
            LocalDate date = LocalDate.now();
            LocalDate expiryDate = product.getTrailSubscribers(email);
            if( expiryDate != null && date.getDayOfMonth() <= expiryDate.getDayOfMonth()
                    && date.getMonthValue() <= expiryDate.getMonthValue() && date.getYear() <= expiryDate.getYear())
                trail.put(product.getProductName(),product.getTrailSubscribers(email));
        }
        if(trail.size() == 0)
            return null;
        return trail;
    }

    public void giftSubscription(String productName, String planName, String coupon, String email) {
        //TODO: // check mail with the company and mail user with the subscription plan
        // Handle payment new PaymentController().processPayment(price,subscriber);
        //notificationService.send();
    }

    public void raiseIssue(String message){
        company.addIssue(new Issue(email,LocalDate.now(),message));
    }

}
