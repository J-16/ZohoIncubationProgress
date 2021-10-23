package com.company.subscriptionmanagement.model;

public class Notification{
    private final long ID;
    private final long subscriberID;
    private final String message;

    private static long generateID = 0;

    public Notification(long subscriberID, String message) {
        this.ID = generateID++;
        this.subscriberID = subscriberID;
        this.message = message;
    }

    public long getID() {
        return ID;
    }

    public long getSubscriberID() {
        return subscriberID;
    }

    public String getMessage() {
        return message;
    }

    public static long getGenerateID() {
        return generateID;
    }

    public static void setGenerateID(long generateID) {
        Notification.generateID = generateID;
    }
}
