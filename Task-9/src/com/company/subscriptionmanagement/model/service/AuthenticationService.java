package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.Company;


public class AuthenticationService {

    private UserDB database;

    public AuthenticationService(UserDB database){
        this.database = database;
    }

    public void register(String name, String email, String password, UserDB.UserType userType){
        if(database.getUserByEmail(email, userType) != null){
            throw new DatabaseException("Account Already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
        }
        database.register(name, email, password, userType);
    }

    public Company login(String email, String password, UserDB.UserType userType){
        Company userAccount = database.getUserByEmail(email, userType);
        if(userAccount == null)
            throw new DatabaseException("No such User found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        if( !userAccount.getPassword().equals(password) )
            throw new DatabaseException("Password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return userAccount;
    }

}