package com.company.companiesuser.controller;

import com.company.companiesuser.dataBase.UserDatabase;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.companiesuser.model.UserAccount;


public class UserAuthenticationController {

    public void register(String name, String email, String password){
        if(name.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, name);
        if(email.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, email);
        if(password.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, password);
        if(UserDatabase.getSubscribersByEmail(email) != null)
            throw new DatabaseException("User already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
        UserDatabase.registerSubscriber(name, email, password);
    }

    public UserAccount login(String email, String password){
        if(email.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, email);
        if(password.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, password);
        UserAccount userAccount = UserDatabase.getSubscribersByEmail(email);
        if( userAccount == null )
            throw new DatabaseException("No such Subscriber found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        if( !userAccount.getPassword().equals(password) )
            throw new DatabaseException("Password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return userAccount;
    }

}