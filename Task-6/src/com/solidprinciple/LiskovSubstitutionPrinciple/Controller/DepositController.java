package com.solidprinciple.LiskovSubstitutionPrinciple.Controller;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.Account;

public class DepositController {
    private final Account account;

    public DepositController(Account account){
        this.account = account;
    }

    public void deposit(double amount){
        account.deposit(amount);
    }
}
