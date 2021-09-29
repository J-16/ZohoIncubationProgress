package com.company.SubscriptionManagement.View;

import com.company.SubscriptionManagement.Controllers.SubscriberController;
import com.company.SubscriptionManagement.Model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {

    private Scanner sc = new Scanner(System.in);
    private SubscriberController subscribeController;
    private String companyName;

    public ProductView(SubscriberController subscriberController, String companyName) {
        this.subscribeController = subscriberController;
        this.companyName = companyName;
    }

    public void productsDetails(){
        ArrayList<String> products = null;
        try{
            products = subscribeController.getProductsByCompany();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        for(String product : products)
            System.out.println(product);
        System.out.println("Enter product you want to subscribe");
        String productName = sc.next();
        displaySubscription(productName);
    }

    public void displaySubscription(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans = null;
        try{
            subscriptionPlans = subscribeController.getSubscriptionPlanByCompany(productName);
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
            System.out.println("0.Quit 1.Use Trail version 2.Subscribe to product");
            int option = sc.nextInt();
            switch(option){
                case 0:
                    return;
                case 1:
                    useTrail(productName);
                    System.out.println("trail version activated");
                    break;
                case 2:
                    subscribeProduct(productName);
                    break;
            }
        }while(true);
    }

    private void useTrail(String productName){
        subscribeController.activateTrail(productName);
    }

    private void subscribeProduct(String productName){
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
            subscribeController.subscribeProduct(productName, planName, coupon);
            System.out.println("Subscription Added successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
