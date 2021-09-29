package com.company.SubscriptionManagement.View;

import com.company.SubscriptionManagement.Controllers.SubscriptionController;
import com.company.SubscriptionManagement.Exception.InvalidException;
import com.company.SubscriptionManagement.Exception.SubscriptionException;
import com.company.SubscriptionManagement.Model.CurrentSubscription;

import java.util.HashMap;
import java.util.Scanner;

public class SubscriberDashboard {

    private SubscriptionController subscriberController;

    public SubscriberDashboard(SubscriptionController subscriberController){
        this.subscriberController = subscriberController;
    }

    public void control(){
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("0.Quit 1.Active Subscription 2.Change subscription plan 3.Subscribe to news letter 4.Notification 5.Gift a Subscription");
            int option = sc.nextInt();
            switch(option){
                case 0:
                    return;
                case 1:
                    activeSubscription();
                    break;
                case 2:
                    changeSubscriptionPlan();
                    break;
                case 3:
                    subscribeNewsLetter();
                    break;
                case 4:
                    notification();
                    break;
                case 5:
                    gitSubscription();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

    private void gitSubscription(){

    }

    private void notification() {
        try {
            for(String notification : subscriberController.getNotification()){
                System.out.println(notification);
            }
        }catch(InvalidException e){
            System.out.println(e.getMessage());
        }
    }

    private void activeSubscription(){
        HashMap<String, CurrentSubscription> subscriptions = subscriberController.getSubscriptionBySubscriber();
        Scanner sc =  new Scanner(System.in);
        if(subscriptions.size() == 0){
            System.out.println("You don't have any active product subscription");
        }
        else {
            subscriptions.forEach((product, currentSubscription)->{
                System.out.println("Product : " + product);
                if(!currentSubscription.isCurrentlySubscribed()){
                    if(currentSubscription.getCancelledDated() == 0)
                        System.out.println("Cancelled Date : " + currentSubscription.getCancelledDated());
                    else {
                        System.out.println("Paused date : " + currentSubscription.getPausedDate());
                        System.out.println("Resume subscription date : " + currentSubscription.getResumeSubscriptionDate());
                    }
                }
                else {
                    System.out.println("Subscription Plan : " + currentSubscription.getSubscriptionPlan().getPlanName());
                    System.out.println("Subscribed Date : " + currentSubscription.getFirstSubscribedDate());
                }
            });
            do{
                System.out.print("0.Quit 1.Cancel Subscription 2.Pause subscription");
                System.out.println();
                int option = sc.nextInt();
                switch(option){
                    case 0 :
                        break;
                    case 1 :
                        cancelSubscription();
                        break;
                    case 2:
                        pauseSubscription();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }while(true);
        }
        System.out.print("0.Quit 1.Cancel news letter Subscription ");
        int option = sc.nextInt();
        if(option == 1)
            unSubscribeNewsletter();
    }

    private void subscribeNewsLetter(){
        System.out.println("Products");
        for(String product : subscriberController.getProductsByCompany()){
            System.out.println(product);
        }
        System.out.println("Enter product names to subscribe newsletter or 0 to quit");
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
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("0.Quit 1.UpGrade 2.DownGrade");
            int option = sc.nextInt();
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
        }catch(InvalidException e){
            System.out.println(e.getMessage());
        }
    }

    private void cancelSubscription(){
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("0.Quit 1.Cancel newsletter subscription 2.Cancel product subscription");
            int option = sc.nextInt();
            switch(option){
                case 0:
                    break;
                case 1:
                    unSubscribeNewsletter();
                    break;
                case 2:
                    System.out.println("Enter Product name to cancel subscription");
                    String productName = sc.next();
                    subscriberController.cancelSubscription(productName);
                    break;
            }
        }while(true);
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
        }catch(SubscriptionException e){
            System.out.println(e.getMessage());
        }
    }

    private void pauseSubscription() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product name to pause subscription");
        String productName = sc.next();
        System.out.println("Enter re subscription date");
        int date = sc.nextInt();
        subscriberController.pauseSubscription(productName, date);
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


}
