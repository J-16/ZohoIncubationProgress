package com.company.SubscriptionManagement.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Product{

    private String productName;
    private int trailDays;
    private double price;
    private boolean isTrailAvailable = false;

    private ArrayList<SubscriptionPlan> subscriptionPlans;
    private ArrayList<Coupon> coupons;
    private HashMap<String, Boolean> newsLetterSubscribers;
    private HashMap<String, CurrentSubscription> productSubscribers; //String - email
    private HashMap<String, Integer> trailSubscribers; // email, startdate

    public Product(String productName, int trailDays, double price){
        this.productName = productName;
        setTrailDays(trailDays);
        this.price = price;
        this.subscriptionPlans = new ArrayList<>();
        this.coupons = new ArrayList<>();
        this.newsLetterSubscribers = new HashMap<>();
        this.productSubscribers = new HashMap<>();
        this.trailSubscribers = new HashMap<>();
    }

    public double getPrice() {
        return price;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public int getTrailDays(){
        return trailDays;
    }

    public void setTrailDays(int trailDays){
        if(trailDays > 0){
            isTrailAvailable = true;
            this.trailDays = trailDays;
        }
    }

    public ArrayList<SubscriptionPlan> getSubscriptionPlan(){
        return subscriptionPlans;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan){
        this.subscriptionPlans.add(subscriptionPlan);
    }

    public ArrayList<Coupon> getCoupons(){
        return coupons;
    }

    public void setCoupons(Coupon coupons){
        this.coupons.add(coupons);
    }

    public HashMap<String, Boolean> getNewsLetterSubscribers(){
        return newsLetterSubscribers;
    }

    public void setNewsLetterSubscribedUsers(String email, Boolean isSubscribed){
        this.newsLetterSubscribers.put(email,isSubscribed);
    }

    public HashMap<String, CurrentSubscription> getProductSubscribers(){
        return productSubscribers;
    }

    public CurrentSubscription getProductSubscribers(String email){
        return productSubscribers.get(email);
    }

    public void addProductSubscribers(String email, CurrentSubscription currentSubscription){
        this.productSubscribers.put(email, currentSubscription);
    }

    public void addTrailSubscribers(String email, Integer startdate){
        this.trailSubscribers.put(email, startdate);
    }

    public boolean isTrailAvailable() {
        return isTrailAvailable;
    }

    public void setTrailAvailable(boolean trailAvailable) {
        isTrailAvailable = trailAvailable;
    }
}