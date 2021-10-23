package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;

public interface SubscriptionPlanDB{
    public void save(SubscriptionPlan issue);
    public void update(SubscriptionPlan updateSubscriptionPlan);
    public ArrayList<SubscriptionPlan> getSubscriptionPlan();
    public SubscriptionPlan getByID(long ID);
}
