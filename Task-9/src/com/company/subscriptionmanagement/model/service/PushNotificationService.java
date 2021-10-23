package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.database.CurrentDatabase;
import com.company.subscriptionmanagement.database.DataStructures.NotificationDS;
import com.company.subscriptionmanagement.database.NotificationDB;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Notification;
import com.company.subscriptionmanagement.model.Subscriber;

public class PushNotificationService implements NotificationService{

    NotificationDB notificationDB;

    public PushNotificationService(){
        notificationDB = CurrentDatabase.getNotificationDB();
    }

    @Override
    public void send(String message, Subscriber subscriber){
        notificationDB.save(new Notification(subscriber.getID(),message));
    }

    @Override
    public void send(Company company, String message){
        //sendNotification(message);
    }

}