package com.company.subscriptionmanagement.model;

import java.util.ArrayList;

public class NewsLetter{

    private final long ID;
    private final long companyID;
    private final long subscribersIDs;
    private final long productID;

    private static long generateId = 0;

    public NewsLetter(long companyID, long subscribersIDs, long productID) {
        ID = generateId++;
        this.companyID = companyID;
        this.productID = productID;
        this.subscribersIDs = subscribersIDs;
    }

    public long getID(){
        return ID;
    }

    public long getCompanyID() {
        return companyID;
    }

    public long getSubscribersIDs() {
        return subscribersIDs;
    }
}
