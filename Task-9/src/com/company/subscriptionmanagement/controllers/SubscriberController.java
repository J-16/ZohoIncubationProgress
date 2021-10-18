package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.*;
import com.company.subscriptionmanagement.model.service.SubscriberService;
import com.company.subscriptionmanagement.model.service.Validity;
import com.company.subscriptionmanagement.view.Dashboard;
import com.company.subscriptionmanagement.view.SubscriberDashboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class SubscriberController{

    private String email;
    private String name;
    private SubscriberService subscriptionService;
    private Company company;
    private CompanyDatabase database = new CompanyDatabase();
    private Dashboard dashboard;

    public SubscriberController(String email, String name, String companyName){
        this.email = email;
        this.name = name;
        this.company = getCompany(companyName);
        this.subscriptionService = new SubscriberService(email, name, company, database);
    }

    public void activateTrail(String productName){
        if(productName == null)
            throw new InputException("product name cannot be empty", InputException.ExceptionType.EMPTY_EXCEPTION, "productName");
        subscriptionService.activateTrail(productName);
    }

    public void subscribeProduct(String productName, String planName, String couponName){
        Validity.emptyCheck("productName", productName);
        subscriptionService.subscribeProduct(productName,planName,couponName);
    }

    public void upgradeSubscriptionPlan(String productName, String subscriptionPlan){
        Validity.emptyCheck("productName", productName,"subscriptionPlan", subscriptionPlan);
        subscriptionService.changeSubscription(productName, subscriptionPlan);
    }

    public void requestDownGrade() {
        //customer care will contact for enquiries, queries will be added to queue
    }

    public void pauseSubscription(String productName, LocalDate resumeDate){
        Validity.emptyCheck("productName", productName);
        Validity.nullCheck("resumeDate", resumeDate);
        subscriptionService.pauseSubscription(productName,resumeDate);
    }

    public void cancelSubscription(String productName){
        Validity.emptyCheck("productName", productName);
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
        dashboard = new SubscriberDashboard(this);
        dashboard.control();
    }

    public ArrayList<String> getProductsByCompany() {
        return subscriptionService.getProductsByCompany();
    }

    public ArrayList<SubscriptionPlan> getAllSubscriptionPlanByCompany(String productName){
        Validity.emptyCheck("productName", productName);
        return subscriptionService.getAllSubscriptionPlanByCompany(productName);
    }

    public HashMap<String,CurrentSubscription> getSubscriptionBySubscriber(){
        return subscriptionService.getSubscriptionBySubscriber();
    }

    public HashMap<String, LocalDate> getTrailSubscribedProducts(){
        return subscriptionService.getTrailSubscribedProducts();
    }

    public boolean getIsTrailAvailable(String productName) {
        Validity.emptyCheck("productName", productName);
        return subscriptionService.getProductByCompany(productName).isTrailAvailable();
    }

    public int getTrailDays(String productName) {
        Validity.emptyCheck("productName", productName);
        return subscriptionService.getProductByCompany(productName).getTrailDays();
    }

    public ArrayList<String> getNotification(){
        return subscriptionService.getNotification();
    }

    public ArrayList<String> getSubscribedNewsletter() {
        return subscriptionService.getSubscribedNewsletter();
    }

    public void giftSubscription(String productName, String planName, String coupon, String email) {
        Validity.emptyCheck("productName", productName, "planName", planName);
        Validity.isValidEmail(email);
        Validity.emptyCheck("email", email);
        subscriptionService.giftSubscription(productName, planName, coupon, email);
    }

    public void raiseIssue(String complain){
        Validity.emptyCheck("complain", complain);
        subscriptionService.raiseIssue(complain);
    }

    private Company getCompany(String companyName){
        Company company = database.getCompanyByName(companyName);
        if (company == null)
            throw new DatabaseException("No company name found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "companyName");
        return company;
    }

    //non functionality
    public static boolean isValidCompany(String companyName){
        return new CompanyDatabase().getCompanyByName(companyName) != null;
    }

}