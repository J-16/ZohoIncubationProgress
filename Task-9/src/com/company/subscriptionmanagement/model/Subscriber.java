package com.company.subscriptionmanagement.model;

import java.util.ArrayList;

public class Subscriber implements Users {

    private Account account;
    private PaymentDetails paymentDetails;
    private final long userId;
    private ArrayList<String> notification;

    private static long ID_GENERATE = 0;

    public Subscriber(String name, String email){
        this.account = new Account(name,email);
        this.notification = new ArrayList<>();
        userId = ID_GENERATE++;
    }

    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    public PaymentDetails getPaymentDetails(){
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails){
        this.paymentDetails = paymentDetails;
    }

    public long getUserId(){
        return userId;
    }

    public void sendNotification(String notification){
        this.notification.add(notification);
    }

    public ArrayList<String> getNotification(){
        return this.notification;
    }

}