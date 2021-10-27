package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.TrailVersion;


import java.util.LinkedList;

public interface TrailSubscribersDB{
    public void save(TrailVersion trailVersion);
    public void update(TrailVersion updateTrailVersion);
    public LinkedList<TrailVersion> getTrailSubscribers();
    public TrailVersion getByID(long ID);
    public TrailVersion getByCompanyID(long companyID, long productID, long subscriberID);
}
