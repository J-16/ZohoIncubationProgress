package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.service.ProductService;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;

public class CompanyController {

    private ProductService productService;
    private Company company;

    public CompanyController(Company company){
        this.company = company;
        this.productService = new ProductService(company);
    }

    public void addProduct(String name, int trailDays, double price){
        productService.addProduct(name, trailDays, price);
    }

    public void addSubscriptionPlan(String productName, String subscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        productService.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, String  newSubscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount) {
        if( !newSubscriptionName.equals("null") )
            productService.updateSubscriptionPlan(productName,subscriptionName, newSubscriptionName);
        if(subscriptionType != null)
            productService.updateSubscriptionPlan(productName,subscriptionName, subscriptionType);
        if(discount > -1)
            productService.updateSubscriptionPlan(productName,subscriptionName, discount);
    }

    public void addCoupon(String productName, String coupon, int expiryDate, double discount) {
        productService.addCoupon(productName, coupon, expiryDate, discount);
    }

    public ArrayList<Product> getProducts(){
        return productService.getProducts();
    }

    public ArrayList<SubscriptionPlan> getSubscriptionPlanByProduct(String productName){
        return productService.getSubscriptionPlanByProduct(productName);
    }

}