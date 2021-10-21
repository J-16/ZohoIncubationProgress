package com.company.companiescustomer.model;

import com.company.subscriptionmanagement.model.Account;
import com.company.subscriptionmanagement.model.ProtectedAccount;

public class Customer{

    private ProtectedAccount account;
    private final int ID;

    private static int IDGenerate = 1;

    public Customer(String name, String email, String password){
        this.account = new ProtectedAccount(name, email, password);
        this.ID = IDGenerate++;
    }

    public void setAccount(Account account) {
        this.account = (ProtectedAccount) account;
    }

    public ProtectedAccount getAccount(){
        return account;
    }

    public int getID(){
        return ID;
    }

}