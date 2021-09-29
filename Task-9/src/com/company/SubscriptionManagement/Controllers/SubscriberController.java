package com.company.SubscriptionManagement.Controllers;

import com.company.SubscriptionManagement.Database.Database;
import com.company.SubscriptionManagement.Exception.InvalidException;
import com.company.SubscriptionManagement.Exception.SubscriptionException;
import com.company.SubscriptionManagement.Model.*;
import com.company.SubscriptionManagement.Model.Service.SubscriberService;
import com.company.SubscriptionManagement.View.SubscriberDashboard;

import java.util.ArrayList;
import java.util.HashMap;

public class SubscriberController {

    private String email;
    private String name;
    private Company company;
    private SubscriberService subscriptionService;

    public SubscriberController(String email, String name){
        this.email = email;
        this.name = name;
    }

    public void activateTrail(String productName) {
        Product product = getProductByCompany(productName);
        isSubscribed(productName);
        ISubscriber subscriber = isSubscriber();
        subscriptionService = new SubscriberService(subscriber);
        subscriptionService.activateTrail(product);
    }

    private ISubscriber isSubscriber() {
        ISubscriber subscriber;
        subscriber = Database.getSubscribersByEmail(email);
        if( subscriber == null ){
            Database.registerSubscriber(email, name);
            subscriber = Database.getSubscribersByEmail(email);
        }
        return subscriber;
    }

    public void subscribeProduct(String productName, String planName, String couponName){
        ArrayList<Product> products = company.getProducts();
        isSubscribed(productName);
        for(Product product : products){
            if(product.getProductName().equals(productName)){
                ArrayList<SubscriptionPlan> subscriptionPlans = product.getSubscriptionPlan();
                for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
                    if(subscriptionPlan.getPlanName().equals(planName)){
                        double price = subscriptionPlan.getPrice();
                        if(couponName != null){
                            Coupon coupon = getCoupon(product, couponName);
                            price = price + (coupon.getDiscount()/100);
                        }
                        ISubscriber subscriber = isSubscriber();
                        subscriptionService = new SubscriberService(subscriber);
                        new PaymentController().processPayment(price,subscriber);
                        subscriptionService.subscribeProduct(product,subscriptionPlan);
                        return;
                    }
                }
                throw new InvalidException("Invalid Subscription plan");
            }
        }
        throw new InvalidException("Invalid product found");
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

    public void upgradeSubscriptionPlan(String productName, String subscriptionPlan){
        Product product = getProductByCompany(productName);
        checkUpgrade(product, subscriptionPlan);
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlan);
        subscriptionService.changeSubscription(product, newSubscriptionPlan);
    }

    private void checkUpgrade(Product product, String subscriptionPlan){
        SubscriptionPlan oldSubscriptionPlan = getSubscriptionPlan(product, product.getProductSubscribers(email).getSubscriptionPlan().getPlanName());
        SubscriptionPlan newSubscriptionPlan = getSubscriptionPlan(product, subscriptionPlan);
        if(oldSubscriptionPlan.getPrice() < newSubscriptionPlan.getPrice())
            throw new SubscriptionException("Your request will be processed soon");
    }

    public void requestDownGrade() {
        //customer care will contact for enquiries, queries will be added to queue
    }

    public void pauseSubscription(String productName, int resumeDate){
        if(resumeDate < 0)
            throw new InvalidException("Invalid date");
        Product product = getProductByCompany(productName);
        subscriptionService.pauseSubscription(product,resumeDate);
    }

    public void cancelSubscription(String productName){
        Product product = getProductByCompany(productName);
        if(product.getProductSubscribers(email) == null)
            throw new InvalidException("Invalid operation");
        subscriptionService.cancelSubscription(product);
    }

    public void setCompany(String companyName) {
        this.company = getCompany(companyName);
    }

    public void unSubscribeNewsletter(String ...productName) {
        for(String product : productName){
            subscriptionService.cancelNewsLetterSubscription(email, getProductByCompany(product));
        }
    }

    public void subscribeNewsletter(String ...productName) {
        for(String product : productName){
            subscriptionService.subscribeNewsLetter(email, getProductByCompany(product));
        }
    }

    public void dashBoard(){
        new SubscriberDashboard(this).control();
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

    public ArrayList<SubscriptionPlan> getSubscriptionPlanByCompany(String productName){
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
        return subscriptions;
    }

    public Product getProductByCompany(String productName){
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        throw new InvalidException("No such product");
    }

    private Company getCompany(String companyName){
        Company companyAccount = Database.getCompanyByName(companyName);
        if(companyAccount == null)
            throw new InvalidException("No company");
        return companyAccount;
    }

    private void isSubscribed(String productName){
        for(Product product : company.getProducts()){
            if(product.getProductName().equals(productName)){
                for(CurrentSubscription currentSubscription: product.getProductSubscribers().values()){
                    if(currentSubscription.getSubscriberEmail().equals(email))
                        throw new SubscriptionException("You have already subscribed to the service please go to dashboard");
                }
            }
        }
    }

    public SubscriptionPlan getSubscriptionPlan(Product product, String newSubscriptionPlan){
        for(SubscriptionPlan subscriptionPlan : product.getSubscriptionPlan()){
            if(subscriptionPlan.getPlanName().equals(newSubscriptionPlan)){
                return subscriptionPlan;
            }
        }
        throw new InvalidException("Invalid subscription name");
    }

    public boolean getIsTrailAvailable(String productName) {
        return getProductByCompany(productName).isTrailAvailable();
    }

    public int getTrailDays(String productName) {
        return getProductByCompany(productName).getTrailDays();

    }

    public ArrayList<String> getNotification(){
        ArrayList<String> notification = subscriptionService.getSubscriberAccount().getNotification();
        if(notification.size() ==0)
            throw new InvalidException("No notification");
        return notification;
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
}