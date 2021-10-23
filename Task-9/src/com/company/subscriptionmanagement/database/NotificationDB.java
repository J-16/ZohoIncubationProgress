package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Notification;

import java.util.ArrayList;

public interface NotificationDB{
    void save(Notification notification);
    ArrayList<String> getNotification(long ID);
    ArrayList<String> getBySubscriberID(long ID);
}
