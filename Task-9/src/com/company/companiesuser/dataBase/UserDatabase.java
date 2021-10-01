package com.company.companiesuser.dataBase;

import com.company.companiesuser.model.UserAccount;

import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, UserAccount> users = new HashMap<>();

    public static UserAccount getSubscribersByEmail(String email){
        return  users.get(email);
    }

    public static void registerSubscriber(String name, String email, String password){
        users.put(email, new UserAccount(name, email,password));
    }

}