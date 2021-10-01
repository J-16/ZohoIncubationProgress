package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.exception.InvalidException;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.ICompany;
import com.company.subscriptionmanagement.model.ISubscriber;
import com.company.subscriptionmanagement.model.Subscriber;

import java.util.HashMap;

public class Database {

    private static HashMap<String, ISubscriber> subscriber = new HashMap<>();
    private static HashMap<String, ICompany> companies = new HashMap<>();
    public static ICompany getCompanyByEmail(String email){
        return companies.get(email);
    }

    public static void register(String name, String email, String password){
        companies.put(email, new Company(name, email, password));
    }

    public static void registerSubscriber(String email, String name){
        subscriber.put(email, new Subscriber(name, email));
    }

    public static ISubscriber getSubscribersByEmail(String email){
        return subscriber.get(email);
    }

    public static HashMap<String, ICompany> getCompanies(){
        return companies;
    }

    public static ICompany getCompanyByName(String companyName){
        for(ICompany company : companies.values()){
            if(company.getAccount().getName().equals(companyName))
                return company;
        }
        throw new InvalidException("No company");
    }

}
