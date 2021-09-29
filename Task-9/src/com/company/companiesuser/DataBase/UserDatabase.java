package com.company.companiesuser.DataBase;

import com.company.companiesuser.Model.UserAccount;

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