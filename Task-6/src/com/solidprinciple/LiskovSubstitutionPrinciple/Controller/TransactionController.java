package com.solidprinciple.LiskovSubstitutionPrinciple.Controller;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.Account;

public class TransactionController {

    private final Account account;

    public TransactionController(Account account){
        this.account = account;
    }

    public void transaction(double amount){
        account.transaction(amount);
    }

}
