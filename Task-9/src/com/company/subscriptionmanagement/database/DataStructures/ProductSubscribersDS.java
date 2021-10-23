package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.ProductSubscribersDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.util.ArrayList;

public class ProductSubscribersDS implements ProductSubscribersDB{

    @Override
    public void save(CurrentSubscription currentSubscription){

    }

    @Override
    public void update(CurrentSubscription updateCurrentSubscription){

    }

    @Override
    public ArrayList<CurrentSubscription> getProductSubscribers(){
        return null;
    }

    @Override
    public CurrentSubscription getByID(long companyID, long subscriberID){
        return null;
    }
}