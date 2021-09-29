package com.company.SubscriptionManagement.Model.Service;

import com.company.SubscriptionManagement.Model.ISubscriber;

public class InvoiceService{

    public String generateInvoice(double price, ISubscriber subscriber){
        return "Payment made for : " + price + "\n" +
                "Name : " + subscriber.getAccount().getName() + "\n" +
                "Email : " + subscriber.getAccount().getEmail();
    }

}
