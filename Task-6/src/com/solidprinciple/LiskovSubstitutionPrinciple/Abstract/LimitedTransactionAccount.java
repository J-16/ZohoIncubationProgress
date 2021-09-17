package com.solidprinciple.LiskovSubstitutionPrinciple.Abstract;

public abstract class LimitedTransactionAccount extends Account{

    private final int TRANSACTION_LIMIT = 2;

    private int transactionCount = 0;

    public LimitedTransactionAccount(long AccountNumber, String name, double balance){
        super(AccountNumber, name, balance);
    }

    public void transaction(double amount){
        if(transactionCount > TRANSACTION_LIMIT){
            System.out.println("Transaction limit exceeded");
            return;
        }
        transactionCount++;
        super.transaction(amount);
    }

}