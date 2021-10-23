package com.company.subscriptionmanagement.database.Files;

import com.company.subscriptionmanagement.database.CurrentSubscriptionDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.io.File;

public class CurrentSubscriptionFile implements CurrentSubscriptionDB{
    private File currentSubscriberFile = new File("currentSubscriber.csv");

    public void save(CurrentSubscription issue){

    }

    public void update(){

    }

    public CurrentSubscription get(){
        return null;
    }
}
