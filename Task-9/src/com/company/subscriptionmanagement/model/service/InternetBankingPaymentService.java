package com.company.subscriptionmanagement.model.service;


public class InternetBankingPaymentService implements PaymentService{

    @Override
    public boolean makePayment(long subscriberID, double price){
        return true;
    }

    @Override
    public boolean makePayment(){
        return true;
    }

}
