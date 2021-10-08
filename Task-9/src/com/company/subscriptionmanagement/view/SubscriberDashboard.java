package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.InvalidOperationException;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class SubscriberDashboard {

    private SubscriberController subscriberController;

    public SubscriberDashboard(SubscriberController subscriberController){
        this.subscriberController = subscriberController;
    }
    
    public void control(){
        try{
            do{
                int option = GetValues.getIntegerValue(0,"0.Previous Menu 1.Active Subscription 2.News Letter 3.Notification 4.Raise an issue");
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
                    default:
                        System.out.println("Invalid option");
                }
            }while(true);
        }catch(DatabaseException | InputException e){
            System.out.println(e.getMessage());
        }
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
        HashMap<String, CurrentSubscription> subscriptions = subscriberController.getSubscriptionBySubscriber();
        Scanner sc =  new Scanner(System.in);
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
        System.out.println();
        do{
            System.out.println();
            int option = GetValues.getIntegerValue(0,"0.Quit 1.Cancel Subscription 2.Pause subscription 3.Change subscription plan");
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
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("1.Cancel product subscription or any other key to quit");
            int option = sc.nextInt();
            if(option != 1)
                return;
            System.out.println("Enter Product name to cancel subscription");
            String productName = sc.next();
            subscriberController.cancelSubscription(productName);
            System.out.println("Subscription cancelled");
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    private void pauseSubscription() {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Product name to pause subscription");
            String productName = sc.next();
            String date = GetValues.getDate("Enter date to resume subscription in yyyy-MM-dd");
            subscriberController.pauseSubscription(productName, LocalDate.parse(date));
        }catch(DatabaseException | InvalidOperationException e){
            System.out.println(e.getMessage());
        }
    }

    private void newsletter(){
        try{
            do{
                int option = GetValues.getIntegerValue(0,"0.Previous Menu 1.Subscribe to newsletter 2.Unsubscribe news letter");
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
        }catch(DatabaseException e){
        System.out.println(e.getMessage());
    }
    }

    private void subscribeNewsLetter(){
        System.out.println("Products");
        for(String product : subscriberController.getProductsByCompany()){
            System.out.println(product);
        }
        System.out.println("Enter product names to subscribe newsletter or 0 for previous Menu");
        Scanner sc = new Scanner(System.in);
        String productName  = sc.nextLine();
        if(productName.equals("0"))
            return;
        subscriberController.subscribeNewsletter(getProductArray(productName));
    }

    private void changeSubscriptionPlan(){
        HashMap<String, CurrentSubscription> subscriptions = subscriberController.getSubscriptionBySubscriber();
        if(subscriptions.size() == 0){
            System.out.println("You don't have any active subscription");
            return;
        }
        do{
            int option = GetValues.getIntegerValue(0,"0.Quit 1.UpGrade 2.DownGrade");
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

    private void upgradeSubscription() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name");
        String productName = sc.next();
        System.out.println("Enter new plan name");
        String planName = sc.next();
        try{
            subscriberController.upgradeSubscriptionPlan(productName, planName);
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    private void unSubscribeNewsletter() {
        try {
            for (String product : subscriberController.getSubscribedNewsletter()) {
                System.out.println(product);
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter product names to unsubscribe");
            String productName = sc.nextLine();
            subscriberController.unSubscribeNewsletter(getProductArray(productName));
        }catch(InvalidOperationException e){
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
            sb.append(prod[i++]);
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your issue");
        String complain = sc.next();
        subscriberController.raiseIssue(complain);
    }

}
