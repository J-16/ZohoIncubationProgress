package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.SubscriptionPlanDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.util.ArrayList;

public class SubscriptionPlansDS implements SubscriptionPlanDB{

    private ArrayList<SubscriptionPlan> subscriptionPlans;

    public SubscriptionPlansDS(){
        subscriptionPlans = new ArrayList<>();
    }

    @Override
    public void save(SubscriptionPlan subscriptionPlan) {
        subscriptionPlans.add(subscriptionPlan);
    }

    @Override
    public void update(SubscriptionPlan updateSubscriptionPlan) {
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            if (subscriptionPlan.getID() == updateSubscriptionPlan.getID() ){
                subscriptionPlans.remove(subscriptionPlan);
                subscriptionPlans.add(updateSubscriptionPlan);
                return;
            }
        }
    }

    @Override
    public ArrayList<SubscriptionPlan> getByCompanyID(long companyID) {
        ArrayList<SubscriptionPlan> companySubscriptionPlans = new ArrayList<>();
        for(SubscriptionPlan updateSubscriptionPlan : subscriptionPlans){
            if (updateSubscriptionPlan.getCompanyID() == companyID)
                companySubscriptionPlans.add(updateSubscriptionPlan);
        }
        return companySubscriptionPlans;
    }

    public ArrayList<SubscriptionPlan> getByProductID(long companyID, long productID){
        ArrayList<SubscriptionPlan> companySubscriptionPlans = new ArrayList<>();
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            if (subscriptionPlan.getCompanyID() == companyID && subscriptionPlan.getProductID() == productID)
                companySubscriptionPlans.add(subscriptionPlan);
        }
        return companySubscriptionPlans;
    }

    @Override
    public SubscriptionPlan getByID(long ID) {
        for(SubscriptionPlan subscriptionPlan : subscriptionPlans){
            if (subscriptionPlan.getCompanyID() == ID)
                return subscriptionPlan;
        }
        return null;
    }
}
