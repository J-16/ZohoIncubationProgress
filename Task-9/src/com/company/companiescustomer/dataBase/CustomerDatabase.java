package com.company.companiescustomer.dataBase;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.Database;

import java.util.HashMap;

public class CustomerDatabase implements Database{

    private static HashMap<String, Customer> users = new HashMap<>();

    public void register(String name, String email, String password){
        users.put(email, new Customer(name, email,password));
    }

    public Customer getUserByEmail(String email){
        return  users.get(email);
    }

}