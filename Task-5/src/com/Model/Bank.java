package com.Model;

import com.Enum.WithDrawMode;

public abstract class Bank{

    private long AtmCardNo;
    private int pin;
    private double balance;

    private static final double MIN_BALANCE = 100D;
    private static final double MIN_TRANSACTION = 100D;
    private static final double MIN_TRANSACTION_CHARGE = 0.02D;
    private static final double MAX_TRANSACTION_CHARGE = 0.04D;

    public Bank(long atmCardNo, int pin) {
        this.AtmCardNo = atmCardNo;
        this.pin = pin;
    }

    public long getAtmCardNo() {
        return AtmCardNo;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void displayBalance(){
        System.out.println("Balance USD : " + this.balance);
    }

    public void withdraw(double amount){
        if(amount < 0) return;

        if(amount % 5 != 0){
            System.out.println("Please correct the amount of value. It should be multiple of USD 5");
            return;
        }

        double charge = 0;

        if(amount >= MIN_TRANSACTION)
            charge = amount * MIN_TRANSACTION_CHARGE ;
        else
            charge =  amount * MAX_TRANSACTION_CHARGE ;

        double withDrawAmount = amount + charge;

        if( (this.balance - withDrawAmount) < MIN_BALANCE || withDrawAmount > this.balance ){
            System.out.println("Insufficient balance");
            return;
        }

       this.balance = this.balance - withDrawAmount;

        System.out.println("Charges USD : " + charge);
        displayBalance();
    }

    public void deposit(double amount){
        if(amount > 0){
            this.balance += amount;
        }
        displayBalance();
    }

    public void withdraw(double amount, WithDrawMode withDrawMode){
        if(amount < 0) return;

        double cashBack = + amount * 0.01;

        if( this.balance < MIN_BALANCE || amount > this.balance ){
            System.out.println("Insufficient balance");
            return;
        }

        this.balance = (this.balance - amount) + cashBack;
        System.out.println("CashBack USD : " + cashBack);
        displayBalance();
    }

}