package com.company.companiescustomer.model;

import com.company.subscriptionmanagement.model.Account;
import com.company.subscriptionmanagement.model.ProtectedAccount;

public class Customers{

    ProtectedAccount account;

    public Customers(String name, String email, String password){
        this.account = new ProtectedAccount(name, email, password);
    }

    public void setAccount(Account account) {
        this.account = (ProtectedAccount) account;
    }

    public ProtectedAccount getAccount(){
        return account;
    }

}
