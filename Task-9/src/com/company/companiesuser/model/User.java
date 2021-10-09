package com.company.companiesuser.model;

import com.company.subscriptionmanagement.model.Account;
import com.company.subscriptionmanagement.model.Information;
import com.company.subscriptionmanagement.model.ProtectedInformation;

public class User implements Account {

    ProtectedInformation account;

    public User(String name, String email, String password){
        this.account = new ProtectedInformation(name, email, password);
    }

    public void setAccount(Information account) {
        this.account = (ProtectedInformation) account;
    }

    public ProtectedInformation getAccount(){
        return account;
    }

}
