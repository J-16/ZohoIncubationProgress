package com.company.subscriptionmanagement.model;

import java.util.ArrayList;

public class Subscriber implements ISubscriber {

    private IAccount account;
    private PaymentDetails paymentDetails;
    private final long userId;
    private ArrayList<String> notification;

    private static long ID_GENERATE = 0;

    public Subscriber(String name, String email){
        this.account = new Account(name,email);
        this.notification = new ArrayList<>();
        userId = ID_GENERATE++;
    }

    @Override
    public IAccount getAccount() {
        return account;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    @Override
    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void sendNotification(String notification){
        this.notification.add(notification);
    }

    @Override
    public ArrayList<String> getNotification(){
        return this.notification;
    }

}