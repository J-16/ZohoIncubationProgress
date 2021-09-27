package com.company.Model.Service;

import com.company.Database.Database;
import com.company.Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductService{

    private CompanyAccount companyAccount;

    public ProductService(CompanyAccount companyAccount){
        this.companyAccount = companyAccount;
    }

    public void addProduct(String name, int trailDays, double price){
        companyAccount.setProducts(new Product(name,trailDays, price));
    }

    public void addSubscriptionPlan(String productName, String planName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        Product product = getProductByName(productName);
        product.setSubscriptionPlan( new SubscriptionPlan(planName, subscriptionType, discount) );
    }

    public void changeSubscriptionPlan(String productName, String planName, String newPlanName, SubscriptionPlan.SubscriptionType newSubscriptionType, double newDiscount){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, planName);
        subscriptionPlan.setPlanName(newPlanName);
        subscriptionPlan.setSubscriptionType(newSubscriptionType);
        subscriptionPlan.setDiscount(newDiscount);
    }

    public void changeSubscriptionPlan(String productName, String planName, String newPlanName){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, planName);
        subscriptionPlan.setPlanName(newPlanName);

    }

    public void changeSubscriptionPlan(String productName, String planName,  SubscriptionPlan.SubscriptionType newSubscriptionType){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, planName);
        subscriptionPlan.setSubscriptionType(newSubscriptionType);
    }

    public void changeSubscriptionPlan(String productName, String planName, String newPlanName, double newDiscount){
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, planName);
        subscriptionPlan.setDiscount(newDiscount);
    }


    public void addCoupon(String productName, String couponName, int expiryDate, double discount){
        Product product = getProductByName(productName);
        product.setCoupons( new Coupon(couponName, expiryDate, discount) );
    }

    public void autoRenewal(){
        ArrayList<Product> products = companyAccount.getProducts();
        for(Product product : products){
            makePaymentByProducts( product );
        }
    }

    private Product getProductByName(String productName){
        ArrayList<Product> products = companyAccount.getProducts();
        for(Product product : products){
            if( productName.equals(product.getProductName()) )
                return product;
        }
        return null;
    }

    private SubscriptionPlan getSubscriptionPlanByName(Product product, String planName){
        ArrayList<SubscriptionPlan> subscriptionPlans = product.getSubscriptionPlan();
        for(SubscriptionPlan  subscriptionPlan : subscriptionPlans){
            if( planName.equals(subscriptionPlan.getPlanName() ) )
                return subscriptionPlan;
        }
        return null;
    }

    private void makePaymentByProducts(Product product){
        HashMap<String, String> productSubscribers = product.getSubscribers();
        productSubscribers.forEach((email, subscriptionName) -> {
            makePayment(product, email, subscriptionName);
        });
    }

    private void makePayment(Product product, String email, String subscriptionName){
        IAccount subscriber = Database.getSubscribersByEmail(email);
        double amount = getSubscriptionPlanByName(product, subscriptionName).getPrice();
        //TODO - Make payment function here
        /* IMPLEMENT */
    }

}
