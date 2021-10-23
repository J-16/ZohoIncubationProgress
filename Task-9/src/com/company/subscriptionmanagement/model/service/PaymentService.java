package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.PaymentDetails;

public interface PaymentService {

    boolean makePayment(long subscriberID, double price);
    boolean makePayment();

}