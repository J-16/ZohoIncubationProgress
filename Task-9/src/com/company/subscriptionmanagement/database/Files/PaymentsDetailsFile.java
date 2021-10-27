package com.company.subscriptionmanagement.database.Files;

import com.company.subscriptionmanagement.database.PaymentDetailsDB;
import com.company.subscriptionmanagement.model.PaymentDetails;

import java.io.File;

public class PaymentsDetailsFile implements PaymentDetailsDB{

    private File paymentDetailsFile = new File("paymentDetails.csv");

    public void save(PaymentDetails issue){

    }

    @Override
    public void update(PaymentDetails updatedPaymentDetail) {

    }

    @Override
    public PaymentDetails getBySubscriberID(long subscriberID) {
        return null;
    }

}
