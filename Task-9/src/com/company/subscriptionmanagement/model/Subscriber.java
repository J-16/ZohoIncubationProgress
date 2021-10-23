package com.company.subscriptionmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Subscriber implements Serializable {

    private Account account;
    private ArrayList<String> notification;

    public Subscriber(String name, String email){
        this.account = new Account(name,email);
        this.notification = new ArrayList<>();
    }

    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    public void sendNotification(String notification){
        this.notification.add(notification);
    }

    public ArrayList<String> getNotification(){
        return notification;
    }

}