package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.ISubscriber;

public class NotificationService{

    public void sendMail(){

    }

    public void sendMail(String message){

    }

    public void sendNotification(String invoice, ISubscriber subscriber){
        subscriber.sendNotification(invoice);
    }

}
