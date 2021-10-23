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

    }

    @Override
    public LinkedList<TrailVersion> getTrailSubscribers() {
        return trailSubscribers;
    }

    @Override
    public TrailVersion getByID(long ID) {
        return null;
    }
}