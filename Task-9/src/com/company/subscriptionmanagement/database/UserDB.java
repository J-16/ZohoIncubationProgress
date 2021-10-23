package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

import java.util.HashMap;

public interface UserDB {

    public enum UserType{
        CUSTOMER, COMPANY, SUBSCRIBER;
    }

    HashMap<String, Subscriber> getSubscriber();
    HashMap<String, Company> getCompanies();
    void register(String name, String email, String password, UserDB.UserType userType);
    Company getUserByEmail(String email, UserDB.UserType userType);
    Company getCompanyByName(String companyName);
    Company getCompanyByID(long ID);
    void registerSubscriber(String email, String name);
    Subscriber getSubscribersByEmail(String email);
    Subscriber getSubscribersByID(long ID);
}