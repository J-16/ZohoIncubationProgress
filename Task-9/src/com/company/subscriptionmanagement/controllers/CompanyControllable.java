package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;
import java.util.ArrayList;

public interface CompanyControllable{

    public void addProduct(String name, int trailDays, double price);
    public void addSubscriptionPlan(String productName, String subscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount);
    public void updateSubscriptionPlan(String productName, String subscriptionName, String  newSubscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount);
    public void addCoupon(String productName, String coupon, LocalDate expiryDate, double discount);
    public ArrayList<Product> getProducts();
    public ArrayList<SubscriptionPlan> getSubscriptionPlanByProduct(String productName);
    public void sendMailToSubscribers(String message);
    public void sendNotificationToSubscribers(String message);

}
