package com.ATM.Model.ConcreteClass;

import com.ATM.Enum.MinMaxConstant;
import com.ATM.Enum.WithDrawMode;
import com.ATM.Exceptions.BalanceExceptions;
import com.ATM.Model.Abstract.Bank;
import com.ATM.Model.BankInterface.IDepositable;
import com.ATM.Model.BankInterface.IWithDrawable;

public class User extends Bank implements IDepositable, IWithDrawable{

    public User(long atmCardNo, int pin){
        super(atmCardNo,pin);
    }

    public void deposit(double amount){
        if(amount < 0)
            throw new BalanceExceptions("Invalid amount");
        setBalance(amount);
    }

    private boolean isValidAmount(double amount){
        return amount>0;
    }

    private boolean isValidWithDrawableAmount(double amount){
        return amount % 5 == 0;
    }

    private double calculateAmount(double amount, MinMaxConstant constant){
        return amount * constant.getValue();
    }

    private boolean checkBalance(double withDrawAmount){
        return (withDrawAmount < getBalance()) && ((getBalance() - withDrawAmount) > MinMaxConstant.MIN_BALANCE.getValue());
    }

    private double calculateWithDrawAmount(double amount){
        double charge = 0;

        if(amount >= MinMaxConstant.MIN_TRANSACTION_CHARGE.getValue())
            charge = calculateAmount(amount, MinMaxConstant.MIN_TRANSACTION_CHARGE);
        else
            charge =  calculateAmount(amount, MinMaxConstant.MAX_TRANSACTION_CHARGE);

        return amount + charge;
    }

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

    private double calculateCashBack(double amount){
        return amount * 0.01;
    }

    public void withdraw(double amount, WithDrawMode withDrawMode){
        if( !isValidAmount(amount) )
            throw new BalanceExceptions("Invalid amount");

        double cashBack = calculateCashBack(amount);

        if( !checkBalance(amount) )
            throw new BalanceExceptions("You don't have enough balance to make this transaction");

        setBalance( (getBalance() - amount) + cashBack);
    }

}
