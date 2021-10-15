package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.InvalidOperationException;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SubscriberDashboard implements Dashboard{

    private SubscriberController subscriberController;

    public SubscriberDashboard(SubscriberController subscriberController){
        this.subscriberController = subscriberController;
    }
    
    public void control(){
        do{
            int option = -1;
            while(option < 0 || option > 4){
                option = GetValues.getIntegerValue("0.Previous Menu 1.Active Subscription 2.News Letter 3.Notification 4.Raise an issue", "Choose a valid option");
            }
            switch(option){
                case 0:
                    return;
                case 1:
                    activeSubscription();
                    break;
                case 2:
                    newsletter();
                    break;
                case 3:
                    notification();
                    break;
                case 4:
                    raiseIssue();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

    private void activeSubscription(){
        HashMap<String, LocalDate> trailSubscriptions = subscriberController.getTrailSubscribedProducts();
        if(trailSubscriptions != null){
            System.out.println("Trail versions");
            trailSubscriptions.forEach((product, date)->{
                System.out.println("Product : " + product + "  Expiry Date : " + date);
            });
        }
        System.out.println();
        HashMap<String, CurrentSubscription> subscriptions;
        try{
            subscriptions = subscriberController.getSubscriptionBySubscriber();
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
            return;
        }
        subscriptions.forEach((product, currentSubscription)->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            System.out.println("Product : " + product);
            if(!currentSubscription.isCurrentlySubscribed()){
                if(currentSubscription.getCancelledDate() != null)
                    System.out.println("Cancelled Date : " + currentSubscription.getCancelledDate().format(formatter));
                else {
                    System.out.println("Paused date : " + currentSubscription.getPausedDate().format(formatter));
                    System.out.println("Resume subscription date : " + currentSubscription.getResumeSubscriptionDate().format(formatter));
                }
            }
            else {
                System.out.println("Subscription Plan : " + currentSubscription.getSubscriptionPlan().getPlanName());
                System.out.println("Subscribed Date : " + currentSubscription.getFirstSubscribedDate().format(formatter));
                System.out.println("Expiry Date : " + currentSubscription.getExpireDate().format(formatter));
            }
        });
        subscriptionOption();
    }

    private void subscriptionOption(){
        System.out.println();
        do{
            System.out.println();
            int option = -1;
            while(option < 0 || option > 3){
                option = GetValues.getIntegerValue("0.Previous Menu 1.Cancel Subscription 2.Pause subscription 3.Change subscription plan", "Choose a valid option");
            }
            switch(option){
                case 0 :
                    return;
                case 1 :
                    cancelSubscription();
                    break;
                case 2:
                    pauseSubscription();
                    break;
                case 3:
                    changeSubscriptionPlan();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

    private void cancelSubscription(){
        while(true){
            try{
                int option = -1;
                while(option < 0){
                    option = GetValues.getIntegerValue("1.Cancel product subscription or any other key to quit", "Cannot be negative");
                }
                if(option != 1)
                    return;
                String productName = GetValues.getString("Enter Product name to cancel subscription");
                subscriberController.cancelSubscription(productName);
                System.out.println("Subscription cancelled");
                return;
            }catch(InvalidOperationException | DatabaseException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void pauseSubscription(){
        String productName = null;
        String date = null;
        while(true){
            try{
                if(productName == null){
                    productName  = GetValues.getString("Enter Product name to pause subscription");
                }
                if(date == null)
                    date = GetValues.getDate("Enter date to resume subscription in yyyy-MM-dd");
                subscriberController.pauseSubscription(productName, LocalDate.parse(date));
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                date = null;
            }catch(InvalidOperationException e){
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    private void newsletter(){
        do{
            int option = -1;
            while(option < 0 || option > 2){
                option = GetValues.getIntegerValue("0.Previous Menu 1.Subscribe to newsletter 2.Unsubscribe news letter", "Choose a valid option");
            }
            switch(option){
                case 0:
                    return;
                case 1:
                    subscribeNewsLetter();
                    break;
                case 2:
                    unSubscribeNewsletter();
            }
        }while(true);
    }

    private void subscribeNewsLetter(){
        System.out.println("Products");
        for(String product : subscriberController.getProductsByCompany()){
            System.out.println(product);
        }

        String productName = GetValues.getLine("Enter product names to subscribe newsletter or 0 for previous Menu");
        if(productName.equals("0"))
            return;
        try{
            subscriberController.subscribeNewsletter(getProductArray(productName));
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
            productName = null;
        }
    }

    private void changeSubscriptionPlan(){
        do{
            int option = -1;
            while(option < 0 || option > 2){
                option = GetValues.getIntegerValue("0.Previous Menu 1.UpGrade 2.DownGrade", "Choose a valid option");
            }
            switch(option){
                case 0:
                    return;
                case 1 :
                    upgradeSubscription();
                    break;
                case 2 :
                    System.out.println("Your request will be processed soon");
                    subscriberController.requestDownGrade();
                    break;
            }
        }while(true);
    }

    private void upgradeSubscription(){
        String productName = null;
        String planName = null;
        while(true){
            try{
                productName = GetValues.getString("Enter product name");
                planName = GetValues.getString("Enter new plan name");
                subscriberController.upgradeSubscriptionPlan(productName, planName);
                return;
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
                productName = null;
                planName = null;
                int s = GetValues.getIntegerValue("enter 0 for previous menu or any other key to continue", "");
                if(s == 0)
                    return;
            }
        }
    }

    private void unSubscribeNewsletter(){
        try {
            System.out.println("Currently subscribed to");
            for (String product : subscriberController.getSubscribedNewsletter()) {
                System.out.println(product);
            }
            String productName = GetValues.getLine("Enter product names to unsubscribe");
            subscriberController.unSubscribeNewsletter(getProductArray(productName));
        }catch(DatabaseException | InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

    private String[] getProductArray(String productName){
        StringBuilder sb = new StringBuilder();
        char[] prod = productName.toCharArray();
        int i =0;
        int len = 0;
        while(i < productName.length()){
            if(prod[i] == ' ' || prod[i] == '\n'){
                len++;
            }
            i++;
        }
        String[] s = new String[len+1];
        len = 0;
        i=0;
        while(i < productName.length()){
            if(prod[i] == ' ' || prod[i] == '\n'){
                s[len++] = sb.toString();
                sb.setLength(0);
            }
            else sb.append(prod[i]);
            i++;
        }
        s[len] = sb.toString();
        return s;
    }

    private void notification() {
        try {
            for(String notification : subscriberController.getNotification()){
                System.out.println(notification);
            }
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    private void raiseIssue() {
        String complain = GetValues.getString("Type your issue");
        subscriberController.raiseIssue(complain);
    }

}
