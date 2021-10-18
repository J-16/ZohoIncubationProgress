package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

public interface NotificationService {

    void send(String message, Subscriber subscriber);
    void send(Company company, String message);

}
