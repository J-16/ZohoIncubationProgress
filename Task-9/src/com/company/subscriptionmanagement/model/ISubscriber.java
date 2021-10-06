package com.company.subscriptionmanagement.model;

import java.util.ArrayList;

public interface ISubscriber{

    Account getAccount();
    PaymentDetails getPaymentDetails();
    void setPaymentDetails(PaymentDetails paymentDetails);
    void sendNotification(String notification);
    ArrayList<String> getNotification();

}