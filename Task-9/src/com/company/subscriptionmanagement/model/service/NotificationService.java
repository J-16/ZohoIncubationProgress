package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.ICompany;
import com.company.subscriptionmanagement.model.ISubscriber;

public class NotificationService{

    public void sendMail(){

    }

    public void sendMail(ICompany company, String message){
        //sendNotification(message);
    }

    public void sendNotification(String invoice, ISubscriber subscriber){
        subscriber.sendNotification(invoice);
    }

    public void sendNotification(ICompany company, String message){
        //sendNotification(message);
    }

}
