package com.ATM.Model;

import com.ATM.Exceptions.BalanceExceptions;

import java.io.Serializable;
import java.util.ArrayList;

class Transaction{
    double amount;
}

//enum TransactionType {
//    CREDITED, DEBITED;
//}

public abstract class Account implements Serializable {

    private final static double MIN_TRANSACTION_CHARGE = 0.02D;
    private final static double MAX_TRANSACTION_CHARGE = 0.04D;
    private final static double MIN_BALANCE = 100D;
    private final static double MIN_TRANSACTION_ALLOWED = 100D;

    private double balance;

    private CustomerInfo customerInfo;

    private ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public Account(String name, long ATMNumber, long pin){
        this.customerInfo = new CustomerInfo(name, ATMNumber, pin);
    }

    public Account(String name, long ATMNumber, long pin, double balance){
        this.customerInfo = new CustomerInfo(name, ATMNumber, pin);
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        this.balance = amount;
    }

    public CustomerInfo getCustomerInfo(){
        return customerInfo;
    }

    public void deposit(double amount){
        if(amount < 0)
            throw new BalanceExceptions("Invalid amount");
        setBalance(getBalance()+amount);
    }

    protected boolean isValidAmount(double amount){
        return amount>0;
    }

    protected boolean isValidWithDrawableAmount(double amount){
        return amount % 5 == 0;
    }

    protected double calculateAmount(double amount, double charge){
        return amount * charge;
    }

    protected boolean checkBalance(double withDrawAmount){
        return (withDrawAmount < getBalance()) && ((getBalance() - withDrawAmount) > Account.MIN_BALANCE);
    }

    protected double calculateWithDrawAmount(double amount){
        double charge = 0;

        if(amount <= Account.MIN_TRANSACTION_ALLOWED)
            charge = calculateAmount(amount, Account.MIN_TRANSACTION_CHARGE);
        else
            charge =  calculateAmount(amount, Account.MAX_TRANSACTION_CHARGE);

        return amount + charge;
    }

    private double calculateCashBack(double amount){
        return amount * 0.01;
    }

    public void swipe(double amount){
        if( !isValidAmount(amount) )
            throw new BalanceExceptions("Invalid amount");

        double cashBack = calculateCashBack(amount);

        if( !checkBalance(amount) )
            throw new BalanceExceptions("You don't have enough balance to make this transaction");

        setBalance( (getBalance() - amount) + cashBack);
    }

    public Transaction getTransaction(){
        return null;
    }

    public Transaction getTransaction(int limit){
        return null;
    }

    @Override
    public String toString(){
        return  customerInfo + " " + " " +  balance;
    }

    public abstract void withdraw(double amount);

}
