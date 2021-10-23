package com.company.subscriptionmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Issue implements Serializable{

    private final long ID;
    private String email;
    private String message;
    private LocalDate date;
    private final long companyID;

    private static long issueIdGenerate = 0;

    public Issue(String email, String message, long companyID){
        this.email = email;
        this.date = LocalDate.now();
        this.message = message;
        this.ID = issueIdGenerate++;
        this.companyID = companyID;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public long getID(){
        return ID;
    }

    public long getCompanyID(){
        return companyID;
    }
}
