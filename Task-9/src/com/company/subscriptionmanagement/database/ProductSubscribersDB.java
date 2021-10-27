package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.CurrentSubscription;
import com.company.subscriptionmanagement.model.TrailVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface ProductSubscribersDB{
    public void save(CurrentSubscription currentSubscription);
    public void update(CurrentSubscription updateCurrentSubscription);
    public ArrayList<CurrentSubscription> getProductSubscribers();
    public CurrentSubscription getByID(long companyID, long subscriberID);
    public CurrentSubscription getByID(long companyID, long subscriberID, long productID);
}