package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.ISubscriber;

public class InvoiceService{

    public String generateInvoice(double price, ISubscriber subscriber){
        return "Payment made for : " + price + "\n" +
                "Name : " + subscriber.getAccount().getName() + "\n" +
                "Email : " + subscriber.getAccount().getEmail();
    }

}
