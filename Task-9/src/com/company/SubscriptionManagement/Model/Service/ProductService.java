package com.company.SubscriptionManagement.Model.Service;

import com.company.SubscriptionManagement.Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductService{

    private Company companyAccount;

    public ProductService(Company companyAccount){
        this.companyAccount = companyAccount;
    }

    public void addProduct(String name, int trailDays, double price){
        companyAccount.setProducts(new Product(name,trailDays, price));
    }

    public void addSubscriptionPlan(Product product, String planName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        product.setSubscriptionPlan( new SubscriptionPlan(planName, subscriptionType, discount, product.getPrice()) );
    }

    public void updateSubscriptionPlan(Product product, SubscriptionPlan subscriptionPlan, String newPlanName, SubscriptionPlan.SubscriptionType newSubscriptionType, double newDiscount){
        subscriptionPlan.setPlanName(newPlanName);
        subscriptionPlan.setSubscriptionType(newSubscriptionType);
        subscriptionPlan.setDiscount(newDiscount);
    }

    public void updateSubscriptionPlan(Product product, SubscriptionPlan subscriptionPlan, String newPlanName){
        subscriptionPlan.setPlanName(newPlanName);
    }

    public void updateSubscriptionPlan(Product product, SubscriptionPlan subscriptionPlan,  SubscriptionPlan.SubscriptionType newSubscriptionType){
        subscriptionPlan.setSubscriptionType(newSubscriptionType);
    }

    public void updateSubscriptionPlan(Product product, SubscriptionPlan subscriptionPlan, double newDiscount){
        subscriptionPlan.setDiscount(newDiscount);
    }

    public void addCoupon(Product product, String couponName, int expiryDate, double discount){
        product.setCoupons( new Coupon(couponName, expiryDate, discount) );
    }

    public void autoRenewal(){
        ArrayList<Product> products = companyAccount.getProducts();
        for(Product product : products){
            PaymentByProduct( product );
        }
    }

    private void PaymentByProduct(Product product){
        HashMap<String, CurrentSubscription> productSubscribers = product.getProductSubscribers();
        PaymentService paymentService = new PaymentService();
        productSubscribers.forEach((email, currentSubscription) -> {
            paymentService.makePayment(product);
        });
    }

}
