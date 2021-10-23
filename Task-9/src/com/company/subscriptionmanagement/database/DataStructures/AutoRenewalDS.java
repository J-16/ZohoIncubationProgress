package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.AutoRenewalDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class AutoRenewalDS implements AutoRenewalDB{

    HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewalDB = new HashMap<>();

    @Override
    public void save(CurrentSubscription currentSubscription){

    }

    @Override
    public void update(CurrentSubscription updateCurrentSubscription){

    }

    @Override
    public void delete(CurrentSubscription updateCurrentSubscription){

    }

    @Override
    public HashMap<LocalDate, LinkedList<CurrentSubscription>> getAutoRenewal(){
        return null;
    }

}
