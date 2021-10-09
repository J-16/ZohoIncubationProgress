package com.company.companiesuser.dataBase;

import com.company.companiesuser.model.User;

import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, User> users = new HashMap<>();

    public User getUserByEmail(String email){
        return  users.get(email);
    }

    public void registerUser(String name, String email, String password){
        users.put(email, new User(name, email,password));
    }

}