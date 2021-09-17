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

    public void deposit(double amount) {
        setBalance(amount);
        System.out.println("Balance : " + getBalance());
    }

    public void withDraw(double amount){
        setBalance(-amount);
        System.out.println("Balance: " + getBalance());
    }

    public void transaction(double amount){
        setBalance(-amount);
        System.out.println("Balance: " + getBalance());
    }

}
