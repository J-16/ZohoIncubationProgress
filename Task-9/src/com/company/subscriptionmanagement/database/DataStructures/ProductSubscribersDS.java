package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.ProductSubscribersDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductSubscribersDS implements ProductSubscribersDB{

    private ArrayList<CurrentSubscription> productSubscribers;

    public  ProductSubscribersDS(){
        productSubscribers = new ArrayList<>();
    }

    @Override
    public void save(CurrentSubscription currentSubscription){
        productSubscribers.add(currentSubscription);
        System.out.println(productSubscribers.size());
    }

    @Override
    public void update(CurrentSubscription updateCurrentSubscription){
        for(CurrentSubscription currentSubscription : productSubscribers){
            if( currentSubscription.getID() == updateCurrentSubscription.getID() ){
                productSubscribers.remove(currentSubscription);
                productSubscribers.add(updateCurrentSubscription);
                return;
            }
        }
    }

    @Override
    public ArrayList<CurrentSubscription> getProductSubscribers(){
        return productSubscribers;
    }

    @Override
    public CurrentSubscription getByID(long companyID, long subscriberID){
        for(CurrentSubscription currentSubscription : productSubscribers){
            if (currentSubscription.getCompanyID() == companyID && currentSubscription.getSubscriberID() == subscriberID)
                return currentSubscription;
        }
        return null;
    }

    @Override
    public CurrentSubscription getByID(long companyID, long subscriberID, long productID){
        for(CurrentSubscription currentSubscription : productSubscribers){
            if (currentSubscription.getCompanyID() == companyID && currentSubscription.getSubscriberID() == subscriberID &&
                currentSubscription.getProductID() == productID)
                return currentSubscription;
        }
        return null;
    }
}