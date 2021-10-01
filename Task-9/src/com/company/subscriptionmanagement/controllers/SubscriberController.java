package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.*;
import com.company.subscriptionmanagement.model.service.SubscriberService;
import com.company.subscriptionmanagement.view.SubscriberDashboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class SubscriberController {

    private String email;
    private String name;
    private SubscriberService subscriptionService;
    private ICompany company;

    public SubscriberController(String email, String name, String companyName){
        this.email = email;
        this.name = name;
        this.company = getCompany(companyName);
        this.subscriptionService = new SubscriberService(email, name, company);
    }

    public void activateTrail(String productName) {
        if(productName == null)
            throw new InputException("product name cannot be empty");
        subscriptionService.activateTrail(productName);
    }

    public void subscribeProduct(String productName, String planName, String couponName){
        subscriptionService.subscribeProduct(productName,planName,couponName);
    }

    public void upgradeSubscriptionPlan(String productName, String subscriptionPlan){
        subscriptionService.changeSubscription(productName, subscriptionPlan);
    }

    public void requestDownGrade() {
        //customer care will contact for enquiries, queries will be added to queue
    }

    public void pauseSubscription(String productName, LocalDate resumeDate){
        subscriptionService.pauseSubscription(productName,resumeDate);
    }

    public void cancelSubscription(String productName){
        subscriptionService.cancelSubscription(productName);
    }

    public void unSubscribeNewsletter(String ...productName) {
        for(String product : productName){
            subscriptionService.cancelNewsLetterSubscription(email,product);
        }
    }

    public void subscribeNewsletter(String ...productName) {
        for(String product : productName){
            subscriptionService.subscribeNewsLetter(email, product);
        }
    }

    public void dashBoard(){
        new SubscriberDashboard(this).control();
    }

    public ArrayList<String> getProductsByCompany() {
        return subscriptionService.getProductsByCompany();
    }

    public ArrayList<SubscriptionPlan> getAllSubscriptionPlanByCompany(String productName){
        return subscriptionService.getAllSubscriptionPlanByCompany(productName);
    }

    public HashMap<String,CurrentSubscription> getSubscriptionBySubscriber(){
        return subscriptionService.getSubscriptionBySubscriber();
    }

    public HashMap<String, LocalDate> getTrailSubscribedProducts(){
        return subscriptionService.getTrailSubscribedProducts();
    }

    public boolean getIsTrailAvailable(String productName) {
        return subscriptionService.getProductByCompany(productName).isTrailAvailable();
    }

    public int getTrailDays(String productName) {
        return subscriptionService.getProductByCompany(productName).getTrailDays();
    }

    public ArrayList<String> getNotification(){
        return subscriptionService.getNotification();
    }

    public ArrayList<String> getSubscribedNewsletter() {
        return subscriptionService.getSubscribedNewsletter();
    }

    private ICompany getCompany(String companyName){
        return Database.getCompanyByName(companyName);
    }

    public void giftSubscription(String productName, String planName, String coupon, String email) {
        subscriptionService.giftSubscription(productName, planName, coupon, email);
    }

    public void raiseIssue(String complain){
        subscriptionService.raiseIssue(complain);
    }

}