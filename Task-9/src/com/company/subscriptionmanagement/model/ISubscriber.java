package com.company.subscriptionmanagement.model;

import java.util.ArrayList;

public interface ISubscriber {
    IAccount getAccount();
    void setAccount(Account account);
    PaymentDetails getPaymentDetails();
    void setPaymentDetails(PaymentDetails paymentDetails);
    long getUserId();
    void sendNotification(String notification);
    ArrayList<String> getNotification();
}