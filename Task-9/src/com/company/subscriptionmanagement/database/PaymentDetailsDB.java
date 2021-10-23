package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.PaymentDetails;

public interface PaymentDetailsDB{
    public void save(PaymentDetails issue);
    public void update();
    public PaymentDetails getBySubscriberID(long subscriberID);
}
