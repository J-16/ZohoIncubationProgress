package com.solidprinciple.LiskovSubstitutionPrinciple.Abstract;

public abstract class WithDrawableAccount extends Account{

    public WithDrawableAccount(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(amount);
        System.out.println("Balance : " + super.getBalance());
    }

    public void withDraw(double amount){
        super.setBalance(-amount);
        System.out.println("Balance: " + super.getBalance());
    }

}