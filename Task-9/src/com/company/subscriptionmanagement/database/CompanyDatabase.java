package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

import java.util.HashMap;

public class CompanyDatabase implements Database{

    private static HashMap<String, Subscriber> subscriber = new HashMap<>();
    private static HashMap<String, Company> companies = new HashMap<>();

    public void register(String name, String email, String password){
        companies.put(email, new Company(name, email, password));
    }

    public Company getUserByEmail(String email){
        return companies.get(email);
    }

    public HashMap<String, Company> getCompanies(){
        return companies;
    }

    public Company getCompanyByName(String companyName){
        for(Company company : companies.values()){
            if(company.getAccount().getName().equals(companyName))
                return company;
        }
        return null;
    }

    public void registerSubscriber(String email, String name){
        subscriber.put(email, new Subscriber(name, email));
    }

    public Subscriber getSubscribersByEmail(String email){
        return subscriber.get(email);
    }

}
