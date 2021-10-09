package com.company.subscriptionmanagement.model;

public class ProtectedInformation extends Information {

    private String password;

    public ProtectedInformation(String name, String email, String password) {
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
