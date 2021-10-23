package com.company.subscriptionmanagement.model;

import java.io.Serializable;

public class ProtectedAccount extends Account implements Serializable {

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
