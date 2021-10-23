package com.company.subscriptionmanagement.model;


public class Company{

    private ProtectedAccount account;

    public Company(String name, String email, String password){
        this.account = new ProtectedAccount(name, email, password);
    }

    public void setAccount(Account account) {
        this.account = (ProtectedAccount) account;
    }

    public ProtectedAccount getAccount(){
        return account;
    }

}