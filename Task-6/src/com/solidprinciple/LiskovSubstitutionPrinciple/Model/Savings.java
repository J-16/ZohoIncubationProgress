package com.solidprinciple.LiskovSubstitutionPrinciple.Model;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.LimitedTransactionAccount;

public class Savings extends LimitedTransactionAccount {
    public Savings(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }
}