package com.company.subscriptionmanagement.model;

import java.time.LocalDate;

public class TrailVersion{

    private final long ID;
    private final long companyID;
    private final long subscriberID;
    private final long productID;
    private final LocalDate expiryDate;
    private static long generateID = 0;
    private boolean isOver = false;

    public TrailVersion(long companyID, long subscriberID, long productID, LocalDate expiryDate) {
        ID = generateID++;
        this.companyID = companyID;
        this.subscriberID = subscriberID;
        this.productID = productID;
        this.expiryDate = expiryDate;
    }


    public long getID() {
        return ID;
    }

    public long getCompanyID() {
        return companyID;
    }

    public long getSubscriberID() {
        return subscriberID;
    }

    public long getProductID() {
        return productID;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public static long getGenerateID() {
        return generateID;
    }

    public static void setGenerateID(long generateID) {
        TrailVersion.generateID = generateID;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
