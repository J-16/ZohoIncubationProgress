package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InvalidOperationException;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Scanner;


public class ProductView {

    private enum SubscriptionType{
        SUBSCRIBE,GIFT;
    }

    private Scanner sc = new Scanner(System.in);
    private SubscriberController subscribeController;
    private String companyName;

    public ProductView(SubscriberController subscriberController, String companyName) {
        this.subscribeController = subscriberController;
        this.companyName = companyName;
    }

    public void productsDetails(){
        ArrayList<String> products;
        try{
            products = subscribeController.getProductsByCompany();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        for(String product : products)
            System.out.println(product);

        System.out.println("");
        int option = GetValues.getIntegerValue(1, "1.Subscribe Product or any other number to go back to previous menu");
        if(option == 1){
            System.out.println("Enter product name to view details");
            String productName = sc.next();
            displaySubscription(productName);
        }
    }

    public void displaySubscription(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans;
        try{
            subscriptionPlans = subscribeController.getAllSubscriptionPlanByCompany(productName);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        if(subscriptionPlans == null){
            System.out.println("No subscription plan to subscribe");
            return;
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            System.out.print("|        plan : "  + subscriptionPlan.getPlanName() + "              ");
        }
        System.out.print("|\n");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            System.out.print("|          ("  + subscriptionPlan.getSubscriptionType() + " )              ");
        }
        System.out.print("  |\n");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            System.out.print("|       Price: "  + subscriptionPlan.getPrice() + "                ");
        }
        System.out.print("|\n");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            System.out.print("|      discount: "  + subscriptionPlan.getDiscount() + "%              ");
        }
        System.out.print("|\n");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        if(subscribeController.getIsTrailAvailable(productName)){
            System.out.println("Trail available for " + subscribeController.getTrailDays(productName));
        }
        do{
            int option = GetValues.getIntegerValue(0,"0.Previous Menu 1.Use Trail version 2.Subscribe to product 3.Gift a Subscription");
            switch(option){
                case 0:
                    return;
                case 1:
                    useTrail(productName);
                    break;
                case 2:
                    subscribeProduct(productName, SubscriptionType.SUBSCRIBE);
                    break;
                case 3:
                    subscribeProduct(productName, SubscriptionType.GIFT);
                    break;
                default:
                    System.out.println("invalid option");
            }
        }while(true);
    }

    private void useTrail(String productName){
        try{
            subscribeController.activateTrail(productName);
            System.out.println("trail version activated");
        }catch(DatabaseException | InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

    private void subscribeProduct(String productName, SubscriptionType type){
        try{
            System.out.println("Enter Plan Name to subscribe");
            String planName = sc.next();
            String coupon = null;
            System.out.println("1.Enter Coupon or any other key to ignore");
            String option = sc.next();
            if (option.equals("1")) {
                System.out.println("Enter coupon : ");
                coupon = sc.next();
            }
            if(type == SubscriptionType.SUBSCRIBE) {
                subscribeController.subscribeProduct(productName, planName, coupon);
                System.out.println("Subscription Added successfully");
                return;
            }
            System.out.println("Enter email you want to gift");
            String email = sc.next();
            subscribeController.giftSubscription(productName, planName, coupon, email);
            System.out.println("Subscription sent");
        }catch(DatabaseException | InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

}
