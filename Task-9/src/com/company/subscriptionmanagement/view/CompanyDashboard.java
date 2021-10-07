package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanyDashboard {

    private Scanner sc = new Scanner(System.in);
    CompanyController companyController;

    public CompanyDashboard(CompanyController companyController){
        this.companyController = companyController;
    }

    public void control(){
        try{
            do{
                int option = getIntegerValue(0, "0.Logout 1.Add product 2.Add Subscription Plan 3.Change Subscription Plan 4.Add Coupon");
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
                        System.out.println("0.Logout 1.Add product 2.Add Subscription Plan 3.Change Subscription Plan 4.Add Coupon");
                }
            }while(true);
        }catch (DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    public void addProduct(){
        System.out.println("Products Details : ");
        int trailDays = -1;
        double price  = -1;
        System.out.println("Enter Product Name");
        String name = sc.next();
        trailDays = getIntegerValue(0,"Enter Trail days if any else 0");
        price = getIntegerValue(0,"Enter Product price");
        companyController.addProduct(name, trailDays, price);
    }

    public void addSubscriptionPlan() {
        String subscriptionName;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        int subType = -1;
        double discount = -1;
        displayProducts();
        System.out.println("Enter Product name");
        String productName =  sc.next();
        System.out.println("Enter Subscription Name");
        subscriptionName = sc.next();
        while( (subType = getIntegerValue(0,"Choose subscription plan 1.Monthly 2.Quarterly 3.Yearly")) > 3){
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
        discount = getDoubleValue(0,"Enter discount if any or 0");
        companyController.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
    }

    public void updateSubscriptionPlan(){
        displayProducts();
        String productName;
        System.out.println("Select Product ");
        productName = sc.next();
        displaySubscriptions(productName);
        String subscriptionName;
        String newSubscriptionName;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        int subType = 10;
        double discount = -1;
        System.out.println("Select Subscription you want to change");
        subscriptionName = sc.next();
        System.out.println("Enter New Subscription Name if applicable or \"null\" for no changes");
        newSubscriptionName = sc.next();
        System.out.println();
        while( subType > 4){
            subType = getIntegerValue(0, "Choose new subscription plan 1.Monthly 2.Quarterly 3.Yearly or 0 for no changes")
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
                    System.out.println("invalid option");
            }
        }
        System.out.println("Enter discount if any or -1 if no changes");
        discount = getDoubleValue(0,"Enter discount if any or 0");
        companyController.updateSubscriptionPlan(productName, subscriptionName, newSubscriptionName, subscriptionType, discount);
    }

    public void addCoupon(){
        displayProducts();
        System.out.println("Select Product");
        String productName = sc.next();
        System.out.println("Enter coupon Code");
        String coupon = sc.next();
        System.out.println("Enter expiry date - yyyy-MM-dd");
        String date = null;
        LocalDate expDate = null;
        boolean isDate = false;
        while(!isDate){
            try{
                System.out.print("Enter Exp date yyyy-MM-dd");
                date = sc.next();
                expDate = LocalDate.parse(date);
                isDate = true;
            }catch(DateTimeParseException e){
                System.out.println("Incorrect date format, enter yyyy-MM-dd");
                date = sc.next();
            }
        }
        double discount = getDoubleValue(1,"Enter discount in % example - 10");
        companyController.addCoupon(productName, coupon, expDate, discount);
    }

    private void displayProducts(){
        ArrayList<Product> products;
        try{
            products = companyController.getProducts();
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
            return;
        }
        products.forEach(product -> {
            System.out.println( product.getProductName() );
        });
    }

    private void displaySubscriptions(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans;
        try{
            subscriptionPlans = companyController.getSubscriptionPlanByProduct(productName);
        }catch(DatabaseException | InputException e){
            System.out.println( e.getMessage() );
            return;
        }
        subscriptionPlans.forEach(subscriptionPlan -> {
            System.out.println( subscriptionPlan.getPlanName() );
        });
    }

    private int getIntegerValue(int minLimit, String message){
        int value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
        return value;
    }

    private double getDoubleValue(int minLimit, String message){
        int value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
        return value;
    }

}
