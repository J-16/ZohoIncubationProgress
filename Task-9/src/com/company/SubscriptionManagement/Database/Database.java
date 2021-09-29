package com.company.SubscriptionManagement.Database;

import com.company.SubscriptionManagement.Model.Company;
import com.company.SubscriptionManagement.Model.ISubscriber;
import com.company.SubscriptionManagement.Model.Subscriber;

import java.util.HashMap;

public class Database {

    private static HashMap<String, ISubscriber> subscriber = new HashMap<>();

    private static HashMap<String, Company> companies = new HashMap<>();

    public static Company getCompanyByEmail(String email) {
        return companies.get(email);
    }

    public static void register(String name, String email, String password){
        companies.put(email, new Company(name, email, password));
    }

    public static void registerSubscriber(String email, String name){
        subscriber.put(email, new Subscriber(name, email));
    }

    public static ISubscriber getSubscribersByEmail(String email){
        return  subscriber.get(email);
    }

    public static HashMap<String, Company> getCompanies(){
        return companies;
    }

    public static Company getCompanyByName(String companyName){
        for(Company company : companies.values()){
            if(company.getAccount().getName().equals(companyName))
                return company;
        }
        return null;
    }

}