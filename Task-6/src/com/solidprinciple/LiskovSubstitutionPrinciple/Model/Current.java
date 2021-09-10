package com.solidprinciple.LiskovSubstitutionPrinciple.Model;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.WithDrawableAccount;

public class Current extends WithDrawableAccount {
    public Current(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }
}