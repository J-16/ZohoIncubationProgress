package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

public class PushNotificationService implements NotificationService{

    @Override
    public void send(String invoice, Subscriber subscriber){
        subscriber.sendNotification(invoice);
    }

    @Override
    public void send(Company company, String message){
        //sendNotification(message);
    }

}