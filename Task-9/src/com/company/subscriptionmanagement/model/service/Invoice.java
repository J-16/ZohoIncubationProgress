package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.Subscriber;

public class Invoice implements InvoiceService{

    public String generateInvoice(double price, Subscriber subscriber){
        return "Payment made for : " + price + "\n" +
                "Name : " + subscriber.getAccount().getName() + "\n" +
                "Email : " + subscriber.getAccount().getEmail();
    }

}
