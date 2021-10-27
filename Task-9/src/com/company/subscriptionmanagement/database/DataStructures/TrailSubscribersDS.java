package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.TrailSubscribersDB;
import com.company.subscriptionmanagement.model.TrailVersion;

import java.util.LinkedList;

public class TrailSubscribersDS implements TrailSubscribersDB{

    private final LinkedList<TrailVersion> trailSubscribers;

    public TrailSubscribersDS() {
        this.trailSubscribers = new LinkedList<>();
    }

    @Override
    public void save(TrailVersion trailVersion) {
        trailSubscribers.add(trailVersion);
    }

    @Override
    public void update(TrailVersion updateTrailVersion){
        for(TrailVersion trailVersion : trailSubscribers){
            if(trailVersion.getID() == updateTrailVersion.getID()){
                trailSubscribers.remove(trailVersion);
                trailSubscribers.add(updateTrailVersion);
                return;
            }
        }
    }

    @Override
    public LinkedList<TrailVersion> getTrailSubscribers() {
        return trailSubscribers;
    }

    @Override
    public TrailVersion getByID(long ID) {
        for(TrailVersion trailVersion : trailSubscribers){
            if(trailVersion.getID() == ID){
                return trailVersion;
            }
        }
        return null;
    }

    public TrailVersion getByCompanyID(long companyID, long productID, long subscriberID){
        for(TrailVersion trailVersion : trailSubscribers){
            if(trailVersion.getCompanyID() == companyID && trailVersion.getProductID() == productID && trailVersion.getSubscriberID() == subscriberID){
                return trailVersion;
            }
        }
        return null;
    }
}