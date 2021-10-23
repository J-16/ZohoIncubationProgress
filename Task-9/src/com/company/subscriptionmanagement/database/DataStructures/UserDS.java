package com.company.subscriptionmanagement.database.DataStructures;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

import java.util.HashMap;

public class UserDS implements UserDB {

    private static HashMap<String, Subscriber> subscriber = new HashMap<>();
    private static HashMap<String, Company> companies = new HashMap<>();

    //Customer as per company
    private static HashMap<String, Customer> customers = new HashMap<>();

    public HashMap<String, Subscriber> getSubscriber(){
        return subscriber;
    }

    public HashMap<String, Company> getCompanies(){
        return companies;
    }

    public void register(String name, String email, String password, UserType userType){
        if(userType == UserType.CUSTOMER)
            customers.put(email, new Customer(name, email,password));
        else
            companies.put(email, new Company(name, email,password));
    }

    public Company getUserByEmail(String email, UserType userType){
        if(userType == UserType.CUSTOMER)
            return customers.get(email);
        else
            return companies.get(email);
    }

    public Company getCompanyByName(String companyName){
        for(Company company : companies.values()){
            if(company.getAccount().getName().equals(companyName))
                return company;
        }
        return null;
    }

    @Override
    public Company getCompanyByID(long ID) {
        return null;
    }

    public void registerSubscriber(String email, String name){
        subscriber.put(email, new Subscriber(name, email));
    }

    public Subscriber getSubscribersByEmail(String email){
        return subscriber.get(email);
    }

    @Override
    public Subscriber getSubscribersByID(long ID) {
        return null;
    }

}
