package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductService{

    private Company company;
    private NotificationService notificationService;

    public ProductService(Company company){
        this.company = company;
    }

    public void addProduct(String name, int trailDays, double price){
        if(company.getProducts() != null){
            for(Product products : company.getProducts()){
                if(products.getProductName().equals(name))
                    throw new DatabaseException("Product already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
            }
        }
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
            throw new DatabaseException("No products found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return products;
    }

    public ArrayList<SubscriptionPlan> getSubscriptionPlanByProduct(String productName){
        ArrayList<Product> products = getProducts();
        if(products.size() == 0)
            throw new DatabaseException("No products found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        ArrayList<SubscriptionPlan> subscriptionPlans = null;
        for(Product product : products){
            if(product.getProductName().equals(productName))
                subscriptionPlans = product.getSubscriptionPlan();
        }
        if(subscriptionPlans == null || subscriptionPlans.size() == 0)
            throw new DatabaseException("No subscription plan is available at the moment for product : " + productName, DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return subscriptionPlans;
    }

    private Product getProductByName(String productName){
        ArrayList<Product> products = getProducts();
        for(Product product : products){
            if(product.getProductName().equals(productName))
                return product;
        }
        throw new DatabaseException("No such product name found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "productName");
    }

    private SubscriptionPlan getSubscriptionPlanByName(Product product, String subscriptionName){
        ArrayList<SubscriptionPlan> subscriptionPlans = product.getSubscriptionPlan();
        if(subscriptionPlans.size() == 0)
            throw new DatabaseException("No subscription plan available so far", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        for(SubscriptionPlan  subscriptionPlan : subscriptionPlans){
            if(subscriptionName.equals(subscriptionPlan.getPlanName()))
                return subscriptionPlan;
        }
        throw new DatabaseException("No such subscription name found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "subscriptionName");
    }

    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void sendMail(String message){
        notificationService = new MailNotificationService();
        notificationService.send(company, message);
    }

    public void sendNotification(String message){
        notificationService = new PushNotificationService();
        notificationService.send(company, message);
    }

}
