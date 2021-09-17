package com.ATM.Controller;

import com.ATM.DataBase.BankDatabase;
import com.ATM.Exceptions.UserException;
import com.ATM.Model.Abstract.Bank;
import com.ATM.Model.ConcreteClass.User;

public class DatabaseController {

    public boolean checkValidCard(long ATMCardNo){
        return  BankDatabase.userDb.containsKey(ATMCardNo);
    }

    public Bank getUser(long ATMCardNo){
        if( !checkValidCard(ATMCardNo) )
            throw new UserException("Invalid Card Number");
        return  BankDatabase.userDb.get(ATMCardNo);
    }

    public void registerUser(long ATMCardNo, int pin){
        BankDatabase.userDb.put( ATMCardNo, new User(ATMCardNo, pin) );
    }

}