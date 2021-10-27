package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;

public interface SubscriptionPlanDB{
    void save(SubscriptionPlan issue);
    void update(SubscriptionPlan updateSubscriptionPlan);
    ArrayList<SubscriptionPlan> getByCompanyID(long companyID);
    ArrayList<SubscriptionPlan> getByProductID(long companyID, long productID);
    SubscriptionPlan getByID(long ID);
}
