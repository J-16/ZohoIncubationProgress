package com.ATM.Model;

import com.ATM.Exceptions.BalanceExceptions;
import com.ATM.Exceptions.TransactionLimitException;

public class SavingsAccount extends Account{

    private final static int TRANSACTION_LIMIT = 2;

    private int transactionCount = 0;

    public SavingsAccount(String name, long ATMNumber, long pin) {
        super(name, ATMNumber, pin);
    }

    @Override
    public void withdraw(double amount){

        if(transactionCount == TRANSACTION_LIMIT)
            throw new TransactionLimitException("Transaction limit exceeded");

        if( !isValidAmount(amount) )
            throw new BalanceExceptions("Invalid amount");

        if(!isValidWithDrawableAmount(amount))
            throw new BalanceExceptions("Please correct the amount of value. It should be multiple of USD 5");

        double withDrawAmount = calculateWithDrawAmount(amount);

        if( !checkBalance(withDrawAmount) )
            throw new BalanceExceptions("You don't have enough balance to make this transaction");
        transactionCount++;
        setBalance(getBalance()-withDrawAmount);
    }

}
