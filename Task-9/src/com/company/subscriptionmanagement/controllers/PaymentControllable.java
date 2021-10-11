package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.Subscriber;

import java.util.HashMap;

public interface PaymentControllable{

    void processPayment(double price, Subscriber subscriber);
    void setPaymentDetails(HashMap<String, String> paymentDetails);
    void setOption(int option);

}
