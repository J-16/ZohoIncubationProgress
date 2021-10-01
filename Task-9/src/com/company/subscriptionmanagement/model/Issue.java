package com.company.subscriptionmanagement.model;

import java.time.LocalDate;

public class Issue{

    private final long issueId;
    private String email;
    private String message;
    private LocalDate date;

    private static long issueIdGenerate = 0;

    public Issue(String email, LocalDate date, String message) {
        this.email = email;
        this.date = date;
        this.message = message;
        this.issueId = issueIdGenerate++;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
