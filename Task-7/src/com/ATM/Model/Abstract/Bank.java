package com.ATM.Model.Abstract;

import com.ATM.Model.BankInterface.IBank;

public abstract class Bank implements IBank{

    private final long AtmCardNo;
    private int pin;
    private double balance;

    public Bank(long atmCardNo, int pin) {
        this.AtmCardNo = atmCardNo;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setPin(int pin){
        this.pin = pin;
    }

    public void setBalance(double amount){
        this.balance += amount;
    }

}