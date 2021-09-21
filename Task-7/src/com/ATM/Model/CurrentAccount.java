package com.ATM.Model;

import com.ATM.Exceptions.BalanceExceptions;

public class CurrentAccount extends Account {

    public CurrentAccount(String name, long ATMNumber, long pin) {
        super(name, ATMNumber, pin);
    }

    @Override
    public void withdraw(double amount){

        if( !isValidAmount(amount) )
            throw new BalanceExceptions("Invalid amount");

        if(!isValidWithDrawableAmount(amount))
            throw new BalanceExceptions("Please correct the amount of value. It should be multiple of USD 5");

        double withDrawAmount = calculateWithDrawAmount(amount);

        if( !checkBalance(withDrawAmount) )
            throw new BalanceExceptions("You don't have enough balance to make this transaction");

        setBalance(-withDrawAmount);
    }

}
