package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.database.*;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
public class ProductService{

    private NotificationService notificationService;
    private Company company;

    private ProductsDB productsDB;
    private SubscriptionPlanDB subscriptionPlanDB;
    private CouponDB couponDB;
    private NewsLetterSubscribersDB newsLetterSubscribersDB;

    public ProductService(Company company){
        this.company = company;
        this.productsDB = CurrentDatabase.getProductsDB();
        this.subscriptionPlanDB = CurrentDatabase.getSubscriptionPlanDBB();
        this.couponDB = CurrentDatabase.getCouponDB();
        this.newsLetterSubscribersDB = CurrentDatabase.getNewsLetterSubscribersDB();
    }

    public void addProduct(String name, int trailDays, double price){
        ArrayList<Product> products = productsDB.getProducts();
        if(products != null){
            for(Product product : products){
                if(product.getProductName().equals(name))
                    throw new DatabaseException("Product already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
            }
        }
        productsDB.save(new Product(name, trailDays, price, company.getAccount().getID()));
    }

    public void addSubscriptionPlan(String productName, String planName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        Product product = getProductByName(productName);
        subscriptionPlanDB.save( new SubscriptionPlan(planName, subscriptionType, discount, product.getPrice(), company.getAccount().getID(), product.getID()) );
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, String newPlanName){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        subscriptionPlan.setPlanName(newPlanName);
        subscriptionPlanDB.update(subscriptionPlan);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName,  SubscriptionPlan.SubscriptionType newSubscriptionType){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        subscriptionPlan.setSubscriptionType(newSubscriptionType);
        subscriptionPlanDB.update(subscriptionPlan);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, double newDiscount){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        subscriptionPlan.setDiscount(newDiscount);
        subscriptionPlanDB.update(subscriptionPlan);
    }

    public void addCoupon(String productName, String couponName, LocalDate expiryDate, double discount){
        Product product = getProductByName(productName);
        couponDB.save( new Coupon(couponName, expiryDate, discount, company.getAccount().getID(), product.getID()) );
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = productsDB.getProducts();
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
                subscriptionPlans = subscriptionPlanDB.getSubscriptionPlan();
        }
        if(subscriptionPlans == null || subscriptionPlans.size() == 0)
            throw new DatabaseException("No subscription plan is available at the moment for product : " + productName, DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return subscriptionPlans;
    }

    public Product getProductByName(String productName){
        ArrayList<Product> products = getProducts();
        for(Product product : products){
            if(product.getProductName().equals(productName))
                return product;
        }
        throw new DatabaseException("No such product found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "productName");
    }

    private SubscriptionPlan getSubscriptionPlanByName(Product product, String subscriptionName){
        ArrayList<SubscriptionPlan> subscriptionPlans = subscriptionPlanDB.getSubscriptionPlan();
        if(subscriptionPlans.size() == 0)
            throw new DatabaseException("No subscription plan available so far", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        for(SubscriptionPlan  subscriptionPlan : subscriptionPlans){
            if(subscriptionName.equals(subscriptionPlan.getPlanName()))
                return subscriptionPlan;
        }
        throw new DatabaseException("No such subscription found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION, "subscriptionName");
    }

    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void sendMails(String message){

    }

    public void sendNotifications(String productName, String message){
        UserDB database = CurrentDatabase.getUserDatabase();
        Product product = getProductByName(productName);
        ArrayList<String> newsLetterSubscribers = newsLetterSubscribersDB.getNewsNewsLetterSubscribers();
        newsLetterSubscribers.forEach( (email) ->{
                    sendNotification(message, database.getSubscribersByEmail(email));
        });
    }

    private void sendMail(String message){
        notificationService = new MailNotificationService();
        notificationService.send(company, message);
    }

    private void sendNotification(String message, Subscriber subscriber){
        notificationService = new PushNotificationService();
        notificationService.send(message,subscriber);
    }

}
