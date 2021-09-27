package com.company.Controllers;

import com.company.Exception.InputException;
import com.company.Exception.InvalidException;
import com.company.Model.CompanyAccount;
import com.company.Model.Product;
import com.company.Model.Service.ProductService;
import com.company.Model.SubscriptionPlan;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.ArrayList;

public class CompanyController {

    private ProductService productService;
    private CompanyAccount company;

    public CompanyController(CompanyAccount company){
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
        ArrayList<Product> products = getProducts();
        productService.addProduct(name, trailDays, price);
    }

    public void addSubscriptionPlan(String productName, String subscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount){
        isValidProductName(productName);
        if( subscriptionName.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if( discount < 0 )
            throw new InputException("discount cannot be negative value");

        productService.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(String productName, String subscriptionName, SubscriptionPlan.SubscriptionType subscriptionType, double discount) {
        isValidSubscriptionPlan(productName, subscriptionName);
        //TODO:
    }

    private void isValidProductName(String productName){
        ArrayList<Product> products = getProducts();
        for(Product product : products){
            if(product.getProductName().equals(productName))
                return;
        }
        throw new InputException("No such product found");
    }

    private void isValidSubscriptionPlan(String productName, String subscriptionPlanName){
        ArrayList<SubscriptionPlan> subscriptionPlans = getSubscriptionPlanByProduct(productName);
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            if(subscriptionPlan.getPlanName().equals(subscriptionPlanName))
                return;
        }
        throw new InputException("No such subscription plan found");
    }

}