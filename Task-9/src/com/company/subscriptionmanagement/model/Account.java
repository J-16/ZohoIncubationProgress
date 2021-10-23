package com.company.subscriptionmanagement.model;

import java.io.Serializable;

public class Account implements Serializable {

    private String name;
    private String email;
    private final long ID;

    private static long generateID = 0;

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
        ID = generateID++;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public long getID() {
        return ID;
    }
}
