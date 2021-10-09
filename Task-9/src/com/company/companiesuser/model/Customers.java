package com.company.companiesuser.model;

import com.company.subscriptionmanagement.model.Users;
import com.company.subscriptionmanagement.model.Account;
import com.company.subscriptionmanagement.model.ProtectedAccount;

public class Customers implements Users {

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
