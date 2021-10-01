package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.ICompany;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.service.ProductService;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyController {

    private ProductService productService;

    public CompanyController(ICompany company){
        this.productService = new ProductService(company);
    }

    public void addProduct(String name, int trailDays, double price){
        if(price < 0)
            throw new InputException("price must be positive value");
        if(trailDays < 0)
            throw new InputException("discount must be positive value");
        productService.addProduct(name, trailDays, price);
    }

    public void addSubscriptionPlan(String productName, String subscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        if( subscriptionName.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if( discount < 0 )
            throw new InputException("discount cannot be negative value");
        productService.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, String  newSubscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount) {
        if( discount < 0 )
            throw new InputException("discount must be positive value");
        if(!newSubscriptionName.equals("null"))
            productService.updateSubscriptionPlan(productName,subscriptionName, newSubscriptionName);
        if(subscriptionType != null)
            productService.updateSubscriptionPlan(productName,subscriptionName, subscriptionType);
        if(discount > -1)
            productService.updateSubscriptionPlan(productName,subscriptionName, discount);
    }

    public void addCoupon(String productName, String coupon, LocalDate expiryDate, double discount) {
        if(discount < 0)
            throw new InputException("discount must be positive value");
        productService.addCoupon(productName, coupon, expiryDate, discount);
    }

    public ArrayList<Product> getProducts(){
        return productService.getProducts();
    }

    public ArrayList<SubscriptionPlan> getSubscriptionPlanByProduct(String productName){
        return productService.getSubscriptionPlanByProduct(productName);
    }

    //TODO: add view
    public void sendMailToSubscribers(String message){
        productService.sendMail(message);
    }

    public void sendNotificationToSubscribers(String message){
        productService.sendNotification(message);
    }

}