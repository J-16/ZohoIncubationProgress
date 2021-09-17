package com.ATM.Model.BankInterface;

public interface IBank{
    int getPin();
    double getBalance();
    void setPin(int pin);
    void setBalance(double amount);
}
