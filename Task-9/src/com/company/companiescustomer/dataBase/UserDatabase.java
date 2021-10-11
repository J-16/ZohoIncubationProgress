package com.company.companiescustomer.dataBase;

import com.company.companiescustomer.model.Customer;

import java.util.HashMap;

public class UserDatabase {

    private static HashMap<String, Customer> users = new HashMap<>();

    public Customer getUserByEmail(String email){
        return  users.get(email);
    }

    public void registerUser(String name, String email, String password){
        users.put(email, new Customer(name, email,password));
    }

}