package com.solidprinciple.LiskovSubstitutionPrinciple.Abstract;

public abstract class Account{
    private long AccountNumber;
    private String name;
    private double balance;

    public Account(long AccountNumber, String name, double balance){
        this.AccountNumber = AccountNumber;
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);
}
