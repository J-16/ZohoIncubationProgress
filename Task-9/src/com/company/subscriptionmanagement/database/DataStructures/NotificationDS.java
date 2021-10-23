package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.NotificationDB;
import com.company.subscriptionmanagement.model.Notification;

import java.util.ArrayList;

public class NotificationDS implements NotificationDB{

    ArrayList<String> notifications = new ArrayList<String>();

    @Override
    public void save(Notification notification) {

    }

    @Override
    public ArrayList<String> getNotification(long ID) {
        return null;
    }

    @Override
    public ArrayList<String> getBySubscriberID(long subscriberID){
        return null;
    }
}
