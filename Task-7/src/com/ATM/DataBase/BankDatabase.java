package com.ATM.DataBase;

import com.ATM.Exceptions.UserException;
import com.ATM.Model.Account;

import java.util.HashMap;

public class BankDatabase {
    public static HashMap<Long, Account> userDb = new HashMap<>();

    public static boolean checkValidCard(long ATMCardNo){
        return  BankDatabase.userDb.containsKey(ATMCardNo);
    }

    public static Account getCustomer(long ATMCardNo){
        if( !checkValidCard(ATMCardNo) )
            throw new UserException("Invalid Card Number");
        return  BankDatabase.userDb.get(ATMCardNo);
    }

    public static void registerCustomer(long ATMNumber, Account account){
        BankDatabase.userDb.put(ATMNumber, account);
    }

}