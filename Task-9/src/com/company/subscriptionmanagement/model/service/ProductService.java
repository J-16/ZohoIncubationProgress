package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.InvalidException;
import com.company.subscriptionmanagement.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductService{

    private ICompany company;

    public ProductService(ICompany company){
        this.company = company;
    }

    public void addProduct(String name, int trailDays, double price){
        company.setProducts(new Product(name,trailDays, price));
    }

    public void addSubscriptionPlan(String productName, String planName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        Product product = getProductByName(productName);

        product.setSubscriptionPlan( new SubscriptionPlan(planName, subscriptionType, discount, product.getPrice()) );
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, String newPlanName){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        subscriptionPlan.setPlanName(newPlanName);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName,  SubscriptionPlan.SubscriptionType newSubscriptionType){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        subscriptionPlan.setSubscriptionType(newSubscriptionType);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, double newDiscount){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        subscriptionPlan.setDiscount(newDiscount);
    }

    public void addCoupon(String productName, String couponName, LocalDate expiryDate, double discount){
        Product product = getProductByName(productName);
        product.setCoupons( new Coupon(couponName, expiryDate, discount) );
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = company.getProducts();
        if(products.size() == 0)
            throw new InvalidException("No products found Exception");
        return products;
    }

    public ArrayList<SubscriptionPlan> getSubscriptionPlanByProduct(String productName){
        ArrayList<Product> products = getProducts();
        if(products.size() == 0)
            throw new InvalidException("No products found Exception");
        ArrayList<SubscriptionPlan> subscriptionPlans = null;
        for(Product product : products){
            if(product.getProductName().equals(productName))
                subscriptionPlans = product.getSubscriptionPlan();
        }
        if(subscriptionPlans == null)
            throw new InputException("Invalid product name");
        return subscriptionPlans;
    }

    private Product getProductByName(String productName){
        ArrayList<Product> products = getProducts();
        for(Product product : products){
            if(product.getProductName().equals(productName))
                return product;
        }
        throw new InputException("No such product found");
    }

    private SubscriptionPlan getSubscriptionPlanByName(Product product, String planName){
        ArrayList<SubscriptionPlan> subscriptionPlans = product.getSubscriptionPlan();
        if( subscriptionPlans.size() == 0)
            throw new InvalidException("No subscription plan available so far");
        for(SubscriptionPlan  subscriptionPlan : subscriptionPlans){
            if( planName.equals(subscriptionPlan.getPlanName() ) )
                return subscriptionPlan;
        }
        throw new InputException("No such subscription");
    }

    public void sendMail(String message){
        new NotificationService().sendMail(company, message);
    }

    public void sendNotification(String message){
        new NotificationService().sendNotification(company, message);
    }

}
