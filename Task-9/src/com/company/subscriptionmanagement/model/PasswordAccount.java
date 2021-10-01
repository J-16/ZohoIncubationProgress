package com.company.subscriptionmanagement.model;

public class PasswordAccount extends Account implements IPasswordAccount {
    private String password;
    public PasswordAccount(String name, String email, String password) {
        super(name, email);
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
