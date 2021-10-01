package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.controllers.PaymentController;
import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.InvalidException;
import com.company.subscriptionmanagement.exception.SubscriptionException;
import com.company.subscriptionmanagement.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class SubscriberService {

    private ISubscriber subscriber;
    private String email;
    private String name;
    private Company company;

    public SubscriberService(String email, String name, Company company){
        this.email = email;
        this.name = name;
        this.company = company;
    }

    public void activateTrail(String productName) {
        Product product = getProductByCompany(productName);
        isSubscribed(productName);
        if(product.isTrailSubscribers(email) != null){
            throw new InvalidException("You have already enabled trail version");
        }
        subscriber = registerSubscriber();
        product.addTrailSubscribers(email, LocalDate.now().plusDays(30));
    }

    public void subscribeProduct(String productName, String planName, String couponName){
        isSubscribed(productName);
        Product product  = getProductByCompany(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByProduct(product, planName);
        double price = subscriptionPlan.getPrice();
        if(couponName != null){
            Coupon coupon = getCoupon(product, couponName);
            price = price + (coupon.getDiscount()/100);
        }
        ISubscriber subscriber = registerSubscriber();
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

    //TODO:Check this working
    public void pauseSubscription(String productName, LocalDate resumeDate){
        if(resumeDate.getDayOfMonth() < LocalDate.now().getDayOfMonth() && resumeDate.getMonthValue() < LocalDate.now().getMonthValue() && resumeDate.getYear() < LocalDate.now().getYear())
            throw new InvalidException("Invalid date");
        Product product = getProductByCompany(productName);
        if(product.getProductSubscribers(email) == null)
            throw new InvalidException("You have not subscribed to perform this operation");
        CurrentSubscription currentSubscription = getProductSubscriberByEmail(product, subscriber.getAccount().getEmail());
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setPausedDate(LocalDate.now());
        currentSubscription.setResumeSubscriptionDate(resumeDate);
        setAutoRenewal(currentSubscription);
    }

    public void cancelSubscription(String productName){
        Product product = getProductByCompany(productName);
        if(product.getProductSubscribers(email) == null)
            throw new InvalidException("You have not subscribed to perform this operation");
        CurrentSubscription currentSubscription = getProductSubscriberByEmail(product, subscriber.getAccount().getEmail());
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setCancelledDate(LocalDate.now());
        currentSubscription.setResumeSubscriptionDate(null);
        cancelAutoRenewal(currentSubscription);
    }

    public void subscribeNewsLetter(String subscriberEmail, String productName){
        Product product = getProductByCompany(productName);
        product.setNewsLetterSubscribedUsers(subscriberEmail, true);
    }

    public void cancelNewsLetterSubscription(String subscriberEmail, String productName){
        Product product = getProductByCompany(productName);
        product.setNewsLetterSubscribedUsers(subscriberEmail, false);
    }

    private CurrentSubscription getProductSubscriberByEmail(Product product, String email){
        HashMap<String, CurrentSubscription> productSubscribers = product.getProductSubscribers();
        return productSubscribers.get(email);
    }

    public SubscriptionPlan getSubscriptionPlan(Product product, String newSubscriptionPlan){
        for(SubscriptionPlan subscriptionPlan : product.getSubscriptionPlan()){
            if(subscriptionPlan.getPlanName().equals(newSubscriptionPlan)){
                return subscriptionPlan;
            }
        }
        throw new InvalidException("Invalid subscription name");
    }

    public Product getProductByCompany(String productName){
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        throw new InvalidException("No such product");
    }

    public ArrayList<String> getProductsByCompany() {
        ArrayList<String> products = new ArrayList<>();
        for(Product product : company.getProducts()){
            products.add(product.getProductName());
        }
        if(products.size() == 0)
            throw new InvalidException("No Products available at the moment");
        return products;
    }

    public void setAutoRenewal(CurrentSubscription currentSubscription){
        HashMap<LocalDate, ArrayList<CurrentSubscription>> autoRenewal = company.getAutoRenewal();
        ArrayList<CurrentSubscription> currentSubscriptions = new ArrayList<>();
        if(autoRenewal.containsKey(currentSubscription.getExpireDate())){
            ArrayList<CurrentSubscription> currentSubscriptionsOne = autoRenewal.get(currentSubscription.getExpireDate());
            currentSubscriptionsOne.remove(currentSubscription);
            currentSubscriptions.addAll(currentSubscriptionsOne);
        }
        currentSubscriptions.add(currentSubscription);
        company.setAutoRenewal(currentSubscription.getExpireDate(),currentSubscriptions);
    }

    private void cancelAutoRenewal(CurrentSubscription currentSubscription){
        HashMap<LocalDate, ArrayList<CurrentSubscription>> autoRenewal = company.getAutoRenewal();
        ArrayList<CurrentSubscription> currentSubscriptions = new ArrayList<>();
        ArrayList<CurrentSubscription> currentSubscriptionsOne = autoRenewal.get(currentSubscription.getExpireDate());
        currentSubscriptionsOne.remove(currentSubscription);
        currentSubscriptions.addAll(currentSubscriptionsOne);
        company.setAutoRenewal(currentSubscription.getExpireDate(),currentSubscriptions);
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
            throw new InvalidException("No subscription plans are available for this product at the moment");
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
            throw new InvalidException("You have not subscribed to any products yet");
        return subscriptions;
    }

    public ArrayList<String> getSubscribedNewsletter() {
        ArrayList<String> newsletter = new ArrayList<String>();
        for(Product product : company.getProducts()){
            if(product.getNewsLetterSubscribers().containsKey(email))
                newsletter.add(product.getProductName());
        }
        if(newsletter.size() == 0)
            throw new SubscriptionException("No newsletter subscriptions so far");
        return newsletter;
    }

    public ArrayList<String> getNotification(){
        ArrayList<String> notification = subscriber.getNotification();
        if(notification == null)
            throw new InvalidException("No notification");
        return notification;
    }

    private ISubscriber registerSubscriber() {
        ISubscriber subscriber;
        subscriber = Database.getSubscribersByEmail(email);
        if( subscriber == null ){
            Database.registerSubscriber(email, name);
            subscriber = Database.getSubscribersByEmail(email);
        }
        return subscriber;
    }

    private Coupon getCoupon(Product product, String couponName) {
        if(product.getCoupons().size() == 0)
            throw new InvalidException("Invalid coupon");

        for(Coupon coupon : product.getCoupons()){
            if(coupon.getCouponName().equals(couponName))
                return coupon;
        }
        throw new InvalidException("Invalid coupon");
    }

    private void isSubscribed(String productName){
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                if(product.getProductSubscribers(email) != null)
                    throw new SubscriptionException("You have already subscribed to the service");
            }
        }
    }

    private void checkUpgrade(Product product, String subscriptionPlan){
        SubscriptionPlan oldSubscriptionPlan = getSubscriptionPlan(product, product.getProductSubscribers(email).getSubscriptionPlan().getPlanName());
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlan);
        if(oldSubscriptionPlan.getPrice() < newSubscriptionPlan.getPrice())
            throw new SubscriptionException("Your request will be processed soon");
    }

    private SubscriptionPlan getSubscriptionPlanByProduct(Product product, String planName) {
        for(SubscriptionPlan subscriptionPlan : product.getSubscriptionPlan()){
            if(subscriptionPlan.getPlanName().equals(planName)){
                return subscriptionPlan;
            }
        }
        throw new InvalidException("Invalid Subscription plan");
    }

    public HashMap<String, LocalDate> getTrailSubscribedProducts() {
        HashMap<String, LocalDate> trail = new HashMap<String, LocalDate>();
        for(Product product : company.getProducts()){
            if(product.isTrailSubscribers(email) != null)
                trail.put(product.getProductName(),product.isTrailSubscribers(email));
        }
        if(trail.size() == 0)
            return null;
        return trail;
    }

    public void giftSubscription(String productName, String planName, String coupon, String email) {
        //TODO: // check mail with the company and mail user with the subscription plan
        // if company doesn't provide such api mail company and proceed.
        new NotificationService().sendMail();
    }

    public void raiseIssue(String message){
        company.addIssue(new Issue(email,LocalDate.now(),message));
    }
}
