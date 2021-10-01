package com.company.companiesuser.controller;

import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.UserException;
import com.company.companiesuser.model.UserAccount;

import static com.company.companiesuser.dataBase.UserDatabase.getSubscribersByEmail;
import static com.company.companiesuser.dataBase.UserDatabase.registerSubscriber;


public class UserAuthenticationController {

    public void register(String name, String email, String password) {
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if(getSubscribersByEmail(email) != null)
            throw new UserException("User already exists");
        registerSubscriber(name, email, password);
    }

    public void login(String email, String password) {
        UserAccount userAccount = getSubscribersByEmail(email);
        if( userAccount == null )
            throw new UserException("No such Subscriber found");
        if( !userAccount.getPassword().equals(password) )
            throw new UserException("Password doesn't match");
    }

}