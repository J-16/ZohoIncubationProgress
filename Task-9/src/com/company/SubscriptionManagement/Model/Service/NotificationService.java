package com.company.SubscriptionManagement.Model.Service;

import com.company.SubscriptionManagement.Model.ISubscriber;

public class NotificationService{

    public void sendMail(){

    }

    public void sendNotification(String invoice, ISubscriber subscriber){
        subscriber.sendNotification(invoice);
    }

}
