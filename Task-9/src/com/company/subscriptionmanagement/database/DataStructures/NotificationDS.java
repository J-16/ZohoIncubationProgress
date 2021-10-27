package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.NotificationDB;
import com.company.subscriptionmanagement.model.Notification;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationDS implements NotificationDB{

    HashMap<Long, Notification> notifications;

    public NotificationDS(){
       notifications = new HashMap<>();
    }

    @Override
    public void save(Notification notification) {
        notifications.put(notification.getSubscriberID(), notification);
    }

    @Override
    public ArrayList<String> getNotification(long ID) {
        return null;
    }

    @Override
    public ArrayList<String> getBySubscriberID(long subscriberID){
        ArrayList<String> subscriberNotifications = new ArrayList<>();
        notifications.forEach( (id,notification) -> {
            if(id == subscriberID){
                subscriberNotifications.add(notification.getMessage());
            }
        });
        return subscriberNotifications;
    }
}
