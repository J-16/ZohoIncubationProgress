package com.company.SubscriptionManagement.Controllers;

import com.company.SubscriptionManagement.Exception.InputException;
import com.company.SubscriptionManagement.Exception.InvalidException;
import com.company.SubscriptionManagement.Model.Company;
import com.company.SubscriptionManagement.Model.Product;
import com.company.SubscriptionManagement.Model.Service.ProductService;
import com.company.SubscriptionManagement.Model.SubscriptionPlan;

import java.util.ArrayList;

public class CompanyController {

    private ProductService productService;
    private Company company;

    public CompanyController(Company company){
        this.company = company;
        this.productService = new ProductService(company);
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

    public void addProduct(String name, int trailDays, double price){
        productService.addProduct(name, trailDays, price);
    }

    public void addSubscriptionPlan(String productName, String subscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        Product product = getProductByName(productName);
        if( subscriptionName.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if( discount < 0 )
            throw new InputException("discount cannot be negative value");
        productService.addSubscriptionPlan(product, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, String  newSubscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount) {
        Product product = getProductByName(productName);
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanByName(product, subscriptionName);
        if( !newSubscriptionName.equals("null") )
            productService.updateSubscriptionPlan(product,subscriptionPlan, newSubscriptionName);
        if(subscriptionType != null)
            productService.updateSubscriptionPlan(product,subscriptionPlan, subscriptionType);
        if(discount > -1)
            productService.updateSubscriptionPlan(product,subscriptionPlan, discount);
    }

    public void addCoupon(String productName, String coupon, int expiryDate, double discount) {
        Product product = getProductByName(productName);
        productService.addCoupon(product, coupon, expiryDate, discount);
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
        return null;
    }
}