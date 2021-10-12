package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.Subscriber;

public interface InvoiceService {

    String generateInvoice(double price, Subscriber subscriber);

}
