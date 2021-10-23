package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.PaymentDetailsDB;
import com.company.subscriptionmanagement.model.PaymentDetails;

public class PaymentDetailsDS implements PaymentDetailsDB{

    @Override
    public void save(PaymentDetails issue) {

    }

    @Override
    public void update() {

    }

    @Override
    public PaymentDetails getBySubscriberID(long subscriberID) {
        return null;
    }
}
