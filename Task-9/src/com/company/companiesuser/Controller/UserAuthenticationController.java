package com.company.companiesuser.Controller;

import com.company.SubscriptionManagement.Controllers.SubscriberController;
import com.company.SubscriptionManagement.Exception.InputException;
import com.company.SubscriptionManagement.Exception.UserException;
import com.company.companiesuser.Model.UserAccount;

import static com.company.companiesuser.DataBase.UserDatabase.getSubscribersByEmail;
import static com.company.companiesuser.DataBase.UserDatabase.registerSubscriber;


public class UserAuthenticationController {

    public void register(String name, String email, String password) {
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if(getSubscribersByEmail(email) != null)
            throw new UserException("User already exists");
        registerSubscriber(name, email, password);
    }

    public SubscriberController login(String email, String password) {
        UserAccount userAccount = getSubscribersByEmail(email);
        if( userAccount == null )
            throw new UserException("No such Subscriber found");
        if( !userAccount.getPassword().equals(password) )
            throw new UserException("Password doesn't match");
        return new SubscriberController(userAccount.getEmail(), userAccount.getName());
    }

}