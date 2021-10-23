package com.company.subscriptionmanagement.database.Files;


import com.company.subscriptionmanagement.database.SubscriptionPlanDB;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.io.File;
import java.util.ArrayList;

public class SubscriptionPlansFile implements SubscriptionPlanDB {
    private File subscriptionPlansFile = new File("subscriptionPlans.csv");

    @Override
    public void save(SubscriptionPlan issue){

    }

    @Override
    public void update(SubscriptionPlan updateSubscriptionPlan) {

    }

    @Override
    public ArrayList<SubscriptionPlan> getSubscriptionPlan() {
        return null;
    }

    @Override
    public SubscriptionPlan getByID(long ID) {
        return null;
    }
}
