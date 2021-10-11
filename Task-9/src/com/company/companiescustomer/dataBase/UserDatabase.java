package com.company.companiescustomer.dataBase;

import com.company.companiescustomer.model.Customers;

import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, Customers> users = new HashMap<>();

    public Customers getUserByEmail(String email){
        return  users.get(email);
    }

    public void registerUser(String name, String email, String password){
        users.put(email, new Customers(name, email,password));
    }

}