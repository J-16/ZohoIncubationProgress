package com.company.subscriptionmanagement.model;


public class Company extends Subscriber{

    private String password;

    public Company(String name, String email, String password){
        super(name, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}