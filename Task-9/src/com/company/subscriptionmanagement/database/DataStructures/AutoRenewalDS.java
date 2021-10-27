package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.AutoRenewalDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class AutoRenewalDS implements AutoRenewalDB{

    HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewalDB;

    public AutoRenewalDS(){
       autoRenewalDB = new HashMap<>();
    }

    @Override
    public void save(CurrentSubscription currentSubscription){
        LinkedList<CurrentSubscription> linkedList = new LinkedList<>();
        autoRenewalDB.forEach((date,currentSubscriptions)->{
            linkedList.addAll(currentSubscriptions);
        });
            linkedList.add(currentSubscription);
        autoRenewalDB.put(LocalDate.now(),linkedList);
    }

    @Override
    public void update(CurrentSubscription updateCurrentSubscription){
        LinkedList<CurrentSubscription> linkedList = new LinkedList<>();
        autoRenewalDB.forEach((date,currentSubscriptions)->{
            currentSubscriptions.forEach(sub->{
                if(updateCurrentSubscription.getSubscriberID() == sub.getSubscriberID()){
                    currentSubscriptions.remove(sub);
                    linkedList.add(updateCurrentSubscription);
                }
            });
        });
        autoRenewalDB.put(LocalDate.now(),linkedList);
    }

    @Override
    public void delete(CurrentSubscription updateCurrentSubscription){
        LinkedList<CurrentSubscription> linkedList = new LinkedList<>();
        autoRenewalDB.forEach((date,currentSubscriptions)->{
            currentSubscriptions.forEach(sub->{
                if(updateCurrentSubscription.getSubscriberID() == sub.getSubscriberID()){
                    currentSubscriptions.remove(sub);
                }
            });
        });
        autoRenewalDB.put(LocalDate.now(),linkedList);
    }

    @Override
    public HashMap<LocalDate, LinkedList<CurrentSubscription>> getAutoRenewal(){
        return autoRenewalDB;
    }

}
