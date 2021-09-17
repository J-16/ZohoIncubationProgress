package com.Model;

public abstract class ATM {
    int balance;
    public abstract void call();

    protected void refillMoney(Bank bank, int amount){
        if( !(bank instanceof BankAgent) || amount < 0)
            return;
        this.balance += amount;
    }
}