package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.CurrentSubscription;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public interface SubscriberControllable {

    void activateTrail(String productName);
    void subscribeProduct(String productName, String planName, String couponName);
    void upgradeSubscriptionPlan(String productName, String subscriptionPlan);
    void requestDownGrade();
    void pauseSubscription(String productName, LocalDate resumeDate);
    void cancelSubscription(String productName);
    void unSubscribeNewsletter(String ...productName);
    void subscribeNewsletter(String ...productName);
    void dashBoard();
    ArrayList<String> getProductsByCompany();
    ArrayList<SubscriptionPlan> getAllSubscriptionPlanByCompany(String productName);
    HashMap<String, CurrentSubscription> getSubscriptionBySubscriber();
    HashMap<String, LocalDate> getTrailSubscribedProducts();
    boolean getIsTrailAvailable(String productName);
    int getTrailDays(String productName);
    ArrayList<String> getNotification();
    ArrayList<String> getSubscribedNewsletter();
    void giftSubscription(String productName, String planName, String coupon, String email);
    void raiseIssue(String complain);

}
