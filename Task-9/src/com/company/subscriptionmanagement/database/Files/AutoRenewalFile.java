package com.company.subscriptionmanagement.database.Files;


import com.company.subscriptionmanagement.database.AutoRenewalDB;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class AutoRenewalFile implements AutoRenewalDB{

    private File autoRenewalFile = new File("autoRenewal.csv");

    @Override
    public void save(CurrentSubscription issue){

    }

    @Override
    public void update(CurrentSubscription updateCurrentSubscription) {

    }

    @Override
    public void delete(CurrentSubscription updateCurrentSubscription) {

    }

    @Override
    public HashMap<LocalDate, LinkedList<CurrentSubscription>> getAutoRenewal() {
        return null;
    }

}
