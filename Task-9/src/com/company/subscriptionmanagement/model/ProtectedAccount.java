package com.company.subscriptionmanagement.model;

public class ProtectedAccount extends Account{

    private String password;

    public ProtectedAccount(String name, String email, String password) {
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
