package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

public class MailNotificationService implements NotificationService{

    @Override
    public void send(String invoice, Subscriber subscriber) {

    }

    @Override
    public void send(Company company, String message){
        //sendNotification(message);
    }

}