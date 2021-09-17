package com.solidprinciple.LiskovSubstitutionPrinciple.Model;


import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.Account;

public class Current extends Account {
    public Current(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }
}