package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.PaymentDetails;

public class InternetBankingPaymentService implements PaymentService{

    @Override
    public boolean makePayment(String email, double price, PaymentDetails paymentDetails){
        return true;
    }

    @Override
    public boolean makePayment(){
        return true;
    }

}
