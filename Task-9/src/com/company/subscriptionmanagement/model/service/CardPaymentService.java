package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.PaymentDetails;

public class CardPaymentService implements PaymentService{

    @Override
    public boolean makePayment(String email, double price, PaymentDetails paymentDetails){
        //TODO - Make payment function here
        /* IMPLEMENT */
        return true;
    }

    @Override
    public boolean makePayment(){
        return true;
    }
}