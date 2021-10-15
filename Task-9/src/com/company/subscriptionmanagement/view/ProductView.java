package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.InvalidOperationException;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;

public class ProductView{

    private enum SubscriptionType{
        SUBSCRIBE,GIFT;
    }

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
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("---------Available Products---------");
        for(String product : products)
            System.out.println(product);
        System.out.println("");
        String productName = null;
        while(true){
            int option = GetValues.getIntegerValue("1.Subscribe Product or any other number to go back to previous menu", "Cannot be negative");
            if(option == 1){
                try{
                    productName = GetValues.getString("Enter product name to view details");
                    displaySubscription(productName);
                    return;
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }catch(InputException e){
                    System.out.println(e.getMessage());
                    productName = null;
                }
            }
            else return;
        }
    }

    private void displaySubscription(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans;
        subscriptionPlans = subscribeController.getAllSubscriptionPlanByCompany(productName);
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
        try{
            if(subscribeController.getIsTrailAvailable(productName)){
                System.out.println("Trail available for " + subscribeController.getTrailDays(productName) + "days");
            }
        }catch (DatabaseException e){
            System.out.println(e.getMessage());
            return;
        }
        do{
            int option = -1;
            while(option < 0 || option > 3)
                option = GetValues.getIntegerValue("0.Previous Menu 1.Use Trail version 2.Subscribe to product 3.Gift a Subscription", "Choose a valid option");
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
            }
        }while(true);
    }

    private void useTrail(String productName){
        try{
            subscribeController.activateTrail(productName);
            ToastMessage.SuccessMessage("trail version activated");
        }catch(InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

    private void subscribeProduct(String productName, SubscriptionType type){
        try{
            String planName = GetValues.getString("Enter Plan Name to subscribe");
            String coupon = null;
            if(type == SubscriptionType.SUBSCRIBE) {
                while(true){
                    try{
                        String option = GetValues.getString("1.Enter Coupon or any other number to ignore");
                        if (option.equals("1")) {
                            coupon = GetValues.getString("Enter coupon : ");
                        }
                        subscribeController.subscribeProduct(productName, planName, coupon);
                        ToastMessage.SuccessMessage("Subscription Added successfully");
                        return;
                    }catch(DatabaseException e){
                        System.out.println(e.getMessage());
                        coupon = null;
                    }
                }
            }
            String email = GetValues.getString("Enter email you want to gift");
            subscribeController.giftSubscription(productName, planName, coupon, email);
            System.out.println("Subscription sent");
        }catch(DatabaseException | InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

}
