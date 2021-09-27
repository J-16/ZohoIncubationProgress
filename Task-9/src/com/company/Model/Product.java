package com.company.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Product{

    private String productName;
    private int trailDays;
    private double price;

    private ArrayList<SubscriptionPlan> subscriptionPlans;
    private ArrayList<Coupon> coupons;
    private ArrayList<String> newsLetterSubscribers;
    private HashMap<String, String> productSubscribers; //String - email, String - subscriptionName

    public Product(String productName, int trailDays, double price){
        this.productName = productName;
        this.trailDays = trailDays;
        this.price = price;
        this.subscriptionPlans = new ArrayList<>();
        this.coupons = new ArrayList<>();
        this.newsLetterSubscribers = new ArrayList<>();
        this.productSubscribers = new HashMap<>();
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
        this.trailDays = trailDays;
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

    public ArrayList<String> getNewsLetterSubscribedUsers(){
        return newsLetterSubscribers;
    }

    public void setNewsLetterSubscribedUsers(String email){
        this.newsLetterSubscribers.add(email);
    }

    public HashMap<String, String> getSubscribers(){
        return productSubscribers;
    }

    public void setSubscribers(String email, String subscribersPlanName){
        this.productSubscribers.put(email, subscribersPlanName);
    }

}