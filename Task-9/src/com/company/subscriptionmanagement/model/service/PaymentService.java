package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.PaymentDetails;

public interface PaymentService {

    boolean makePayment(String email, double price, PaymentDetails paymentDetails);
    boolean makePayment();

}
