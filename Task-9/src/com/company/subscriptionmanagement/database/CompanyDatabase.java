package com.company.subscriptionmanagement.database;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Subscriber;

import java.util.HashMap;

public class CompanyDatabase<T extends Customer>{

    public enum UserType{
        CUSTOMER, COMPANY;
    }

    private static HashMap<String, Subscriber> subscriber = new HashMap<>();
    private static HashMap<String, Company> companies = new HashMap<>();

    //Customer as per company
    private static HashMap<String, Customer> customers = new HashMap<>();

    public static HashMap<String, Subscriber> getSubscriber() {
        return subscriber;
    }

    public static HashMap<String, Customer> getUsers() {
        return customers;
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

    public Customer getUserByEmail(String email, UserType userType){
        if(userType == UserType.CUSTOMER)
            return customers.get(email);
        else
            return companies.get(email);
    }

//    public <T extends Customer> T getUserByEmail(String email, UserType userType){
//        if(userType == UserType.CUSTOMER)
//            return (T) customers.get(email);
//        else
//            return (T) companies.get(email);
//    }

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
