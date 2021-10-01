package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.InvalidException;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Scanner;

public class CompanyDashboard {

    private Scanner sc = new Scanner(System.in);
    CompanyController companyController;

    public CompanyDashboard(CompanyController companyController){
        this.companyController = companyController;
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
        double discount = -1;

        while(productName == null){
            displayProducts();
            System.out.println("Enter Product name");
            productName = sc.next();
        }


        System.out.println("Enter Subscription Name");
        subscriptionName = sc.next();

        while(subType < 0){
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
                default:
                    System.out.println("Choose a valid subscription plan");
            }
        }

        while(discount < 0){
            System.out.println("Enter discount if any or 0");
            discount = sc.nextDouble();
        }
        companyController.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(){
        displayProducts();
        String productName = null;
        System.out.println("Select Product ");
        productName = sc.next();

        displaySubscriptions(productName);

        String subscriptionName = null;
        String newSubscriptionName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        int subType = -1;
        Double discount = null;

        System.out.println("Select Subscription you want to change");
        subscriptionName = sc.next();

        System.out.println("Enter New Subscription Name if applicable or \"null\" for no changes");
        newSubscriptionName = sc.next();
        System.out.println("Choose new subscription plan 1.Monthly 2.Quarterly 3.Yearly or 0 for no changes");
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

        System.out.println("Enter discount if any or -1 if no changes");
        discount = sc.nextDouble();

        companyController.updateSubscriptionPlan(productName, subscriptionName, newSubscriptionName, subscriptionType, discount);
    }

    public void addCoupon(){
        displayProducts();
        System.out.println("Select Product");
        String productName = sc.next();
        System.out.println("Enter coupon Code");
        String coupon = sc.next();
        int expDate = -1;
        while(expDate < 1){
            System.out.println("Enter expiry date");
            expDate = sc.nextInt();
        }
        double discount = 0;
        while(discount < 1){
            System.out.println("Enter discount");
            discount = sc.nextDouble();
        }
        companyController.addCoupon(productName, coupon, expDate, discount);
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

    private void displaySubscriptionsByName(String productName, String subscriptionName){
        ArrayList<SubscriptionPlan> subscriptionPlans = null;
        try{
            subscriptionPlans = companyController.getSubscriptionPlanByProduct(productName);
        }catch(InvalidException | InputException e){
            System.out.println( e.getMessage() );
            return;
        }
        System.out.println("Plan Name     |     Subscription Plan   |    Discount    |     price");
        subscriptionPlans.forEach(subscriptionPlan -> {
            if(subscriptionPlan.getPlanName().equals(subscriptionName))
                System.out.println( subscriptionPlan.getPlanName() + " " +  subscriptionPlan.getSubscriptionType() + " " +
                        subscriptionPlan.getDiscount() + " " + subscriptionPlan.getPrice());
        });
    }

}
