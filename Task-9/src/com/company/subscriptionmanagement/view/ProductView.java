package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.InvalidOperationException;
import com.company.subscriptionmanagement.exception.TransactionException;
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
            int option = -1;
            while(option < 0 || option > 1){
                option = GetValues.getIntegerValue("0.Go back 1.Subscribe Product", "Invalid option");
            }
            if(option == 0)
                return;
            else{
                try{
                    if(productName == null)
                        productName = GetValues.getString("Enter product name to view details");
                    displaySubscription(productName);
                    return;
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                    productName = null;
                }catch(InputException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void displaySubscription(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans;
        subscriptionPlans = subscribeController.getAllSubscriptionPlanByCompany(productName);
        DisplayMessage.border("------------------------------------------------------------------------------------------------------------");
        System.out.println();
        DisplayMessage.border("|");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            DisplayMessage.listHeading("    Subscription plan name         ");
            DisplayMessage.border("|");
        }
        DisplayMessage.border("\n");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            DisplayMessage.border("|");
            System.out.print("              "  + subscriptionPlan.getPlanName() + "              ");
        }
        DisplayMessage.border("|\n");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            DisplayMessage.border("|");
            System.out.print("          ("  + subscriptionPlan.getSubscriptionType() + ")              ");
        }
        DisplayMessage.border("|\n");
        DisplayMessage.border("------------------------------------------------------------------------------------------------------------");
        System.out.println();
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            DisplayMessage.border("|");
            System.out.print("       Price: "  + subscriptionPlan.getPrice() + "                ");
        }
        DisplayMessage.border("|\n");
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            DisplayMessage.border("|");
            System.out.print("      discount: "  + subscriptionPlan.getDiscount() + "%              ");
        }
        DisplayMessage.border("|\n");
        DisplayMessage.border("------------------------------------------------------------------------------------------------------------");
        System.out.println();
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
            DisplayMessage.successMessage("trail version activated");
        }catch(InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

    private void subscribeProduct(String productName, SubscriptionType type){
        try{
            String planName = GetValues.getString("Enter Subscription Plan Name to subscribe");
            String coupon = null;
            if(type == SubscriptionType.SUBSCRIBE) {
                while(true){
                    try{
                        String option = GetValues.getString("1.Enter Coupon or any other key to ignore");
                        if (option.equals("1")) {
                            coupon = GetValues.getString("Enter coupon : ");
                        }
                        subscribeController.subscribeProduct(productName, planName, coupon);
                        DisplayMessage.successMessage("Subscription Added successfully");
                        return;
                    }catch(DatabaseException e){
                        System.out.println(e.getMessage());
                        coupon = null;
                    }
                    catch(TransactionException e ){
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            }
            String email = GetValues.getString("Enter email you want to gift");
            subscribeController.giftSubscription(productName, planName, coupon, email);
            DisplayMessage.successMessage("Gifted subscription to " + email);
        }catch(DatabaseException | InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

}
