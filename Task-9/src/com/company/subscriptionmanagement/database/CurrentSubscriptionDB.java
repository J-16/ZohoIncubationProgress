package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.CurrentSubscription;

public interface CurrentSubscriptionDB{
    public void save(CurrentSubscription issue);
    public void update();
    public CurrentSubscription get();
}
