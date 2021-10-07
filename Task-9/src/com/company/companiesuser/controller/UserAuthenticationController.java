package com.company.companiesuser.controller;

import com.company.companiesuser.dataBase.UserDatabase;
import com.company.subscriptionmanagement.exception.ExceptionType;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.companiesuser.model.UserAccount;


public class UserAuthenticationController {

    public void register(String name, String email, String password){
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if(UserDatabase.getSubscribersByEmail(email) != null)
            throw new DatabaseException("User already exists", ExceptionType.EXISTS_EXCEPTION);
        UserDatabase.registerSubscriber(name, email, password);
    }

    public UserAccount login(String email, String password){
        UserAccount userAccount = UserDatabase.getSubscribersByEmail(email);
        if( userAccount == null )
            throw new DatabaseException("No such Subscriber found", ExceptionType.NOT_FOUND_EXCEPTION);
        if( !userAccount.getPassword().equals(password) )
            throw new DatabaseException("Password doesn't match", ExceptionType.NOT_FOUND_EXCEPTION);
        return userAccount;
    }

}