package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public interface AutoRenewalDB{
    public void save(CurrentSubscription issue);
    public void update(CurrentSubscription updateCurrentSubscription);
    public void delete(CurrentSubscription updateCurrentSubscription);
    public HashMap<LocalDate, LinkedList<CurrentSubscription>> getAutoRenewal();
}
