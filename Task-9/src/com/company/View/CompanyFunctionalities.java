package com.company.View;

import com.company.Controllers.CompanyController;
import com.company.Exception.InputException;
import com.company.Exception.InvalidException;
import com.company.Model.CompanyAccount;
import com.company.Model.Product;
import com.company.Model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Scanner;

public class CompanyFunctionalities {

    private Scanner sc = new Scanner(System.in);
    CompanyController companyController;
    CompanyAccount company;

    public CompanyFunctionalities(CompanyAccount company){
        this.company = company;
        this.companyController = new CompanyController(company);
    }

    public void control(){
        do{
            System.out.println("0.Quit 1.Add product 2.Add Subscription Plan 3.Change Subscription Plan 4.Add Coupon");
            int option = sc.nextInt();
            switch(option){
                case 0:
                    return;
                case 1:
                    addProduct();
                    break;
                case 2:
                    addSubscriptionPlan();
                    break;
                case 3:
                    updateSubscriptionPlan();
                    break;
                case 4:
                    addCoupon();
                    break;
                default :
                    System.out.println("Invalid option");
            }
        }while(true);
    }

    public void addProduct(){
        System.out.println("Products Details : ");
        String name = null;
        int trailDays = -1;
        Double price = null;

        while(name == null){
            System.out.println("Enter Product Name");
            name = sc.next();
        }

        while(trailDays == -1){
            System.out.println("Enter Trail days if any else 0");
            trailDays = sc.nextInt();
        }

        while(price == null){
            System.out.println("Enter Product price");
            price = sc.nextDouble();
        }

        companyController.addProduct(name, trailDays, price);
    }

    public void addSubscriptionPlan() {
        String productName = null;
        String subscriptionName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        int subType = -1;
        Double discount = null;

        while(productName == null){
            displayProducts();
            System.out.println("Enter Product name");
            productName = sc.next();
        }

        while(subscriptionName == null){
            System.out.println("Enter Subscription Name");
            subscriptionName = sc.next();
        }

        while(subType == -1){
            System.out.println("Choose subscription plan 1.Monthly 2.Quarterly 3.Yearly");
            subType = sc.nextInt();
            switch(subType){
                case 1:
                    subscriptionType = SubscriptionPlan.SubscriptionType.MONTHLY;
                    break;
                case 2:
                    subscriptionType = SubscriptionPlan.SubscriptionType.QUARTERLY;
                    break;
                case 3:
                    subscriptionType = SubscriptionPlan.SubscriptionType.YEARLY;
            }
        }

        while(discount == null){
            System.out.println("Enter discount if any");
            discount = sc.nextDouble();
        }
        companyController.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(){
        displayProducts();
        String productName = null;
        System.out.println("Enter product Name");
        productName = sc.next();

        displaySubscriptions(productName);

        String subscriptionName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        int subType = -1;
        Double discount = null;

        System.out.println("Enter Subscription Name");
        subscriptionName = sc.next();

        System.out.println("Choose subscription plan 1.Monthly 2.Quarterly 3.Yearly");
        subType = sc.nextInt();
        switch(subType){
            case 1:
                subscriptionType = SubscriptionPlan.SubscriptionType.MONTHLY;
                break;
            case 2:
                subscriptionType = SubscriptionPlan.SubscriptionType.QUARTERLY;
                break;
            case 3:
                subscriptionType = SubscriptionPlan.SubscriptionType.YEARLY;
            }

        System.out.println("Enter discount if any");
        discount = sc.nextDouble();

        companyController.updateSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void addCoupon() {

    }

    private void displayProducts(){
        ArrayList<Product> products = null;
        try{
            products = companyController.getProducts();
        }catch(InvalidException e){
            System.out.println(e.getMessage());
            return;
        }
        products.forEach(product -> {
            System.out.println( product.getProductName() );
        });
    }

    private void displaySubscriptions(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans = null;
        try{
            subscriptionPlans = companyController.getSubscriptionPlanByProduct(productName);
        }catch(InvalidException | InputException e){
            System.out.println( e.getMessage() );
            return;
        }
        subscriptionPlans.forEach(subscriptionPlan -> {
            System.out.println( subscriptionPlan.getPlanName() );
        });
    }

}
