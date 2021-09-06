package com.DataBase;

import com.Exceptions.UserNotFoundException;
import com.Model.Bank;
import com.Model.User;
import java.util.HashMap;

public class BankDatabase {
    private HashMap<Long, Bank> userDb = new HashMap<>();

    {
        registerUser(987456,1234);
        registerUser(987453,1234);
    }

    public void registerUser(long ATMCardNo, int pin){
        userDb.put(ATMCardNo, new User(ATMCardNo, pin));
    }

    public Bank getUser(long ATMCardNo){
        if(userDb.containsKey(ATMCardNo))
            return userDb.get(ATMCardNo);
        else throw new UserNotFoundException();
    }

}
