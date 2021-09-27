package com.company.Database;

import com.company.Model.CompanyAccount;
import com.company.Model.IAccount;

import java.util.HashMap;

public class Database {

    private static HashMap<String, IAccount> subscribers = new HashMap<>();
    private static HashMap<String, CompanyAccount> companies = new HashMap<>();

    public static IAccount getSubscribersByEmail(String email){
        return subscribers.get(email);
    }

    public static CompanyAccount getCompanyByEmail(String email) {
        return companies.get(email);
    }

    public static void register(String name, String email, String password){
        companies.put(email, new CompanyAccount(name, email, password));
    }

}
