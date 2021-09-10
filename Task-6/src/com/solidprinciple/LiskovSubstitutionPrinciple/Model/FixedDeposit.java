package com.solidprinciple.LiskovSubstitutionPrinciple.Model;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.Account;

public class FixedDeposit extends Account {

    public FixedDeposit(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(amount);
        System.out.println("Fixed deposit Balance : " + super.getBalance());
    }

}