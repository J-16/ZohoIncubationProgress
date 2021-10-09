package com.company.subscriptionmanagement.model;

import java.util.HashMap;

public interface Database {

    void register(String name, String email, String password);
    void registerSubscriber(String email, String name);
    Subscriber getSubscribersByEmail(String email);
    HashMap<String, Company> getCompanies();
    Company getCompanyByName(String companyName);

}
