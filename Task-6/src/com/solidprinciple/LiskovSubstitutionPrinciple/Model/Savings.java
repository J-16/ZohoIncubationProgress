package com.solidprinciple.LiskovSubstitutionPrinciple.Model;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.WithDrawableAccount;

public class Savings extends WithDrawableAccount{
    public Savings(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }
}