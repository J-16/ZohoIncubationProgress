package com.company.subscriptionmanagement.model.service;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;


public class AuthenticationService {

    private CompanyDatabase database;

    public AuthenticationService(){
        database = new CompanyDatabase();
    }

    public void register(String name, String email, String password, CompanyDatabase.UserType userType){
        Validity.emptyCheck(name);
        Validity.emptyCheck(email, password);
        validity(email, password);
        if(database.getUserByEmail(email, userType) != null){
            throw new DatabaseException("Account Already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
        }
        database.register(name, email, password, userType);
    }

    public Customer login(String email, String password, CompanyDatabase.UserType userType){
        Validity.emptyCheck(email, password);
        Customer userAccount = database.getUserByEmail(email, userType);
        validity(email, password);
        if( userAccount == null )
            throw new DatabaseException("No such User found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        if( !userAccount.getAccount().getPassword().equals(password) )
            throw new DatabaseException("Password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return userAccount;
    }

    private void validity(String email, String password){
        if(!Validity.isValidEmail(email))
            throw new InputException("Invalid email format", InputException.ExceptionType.INVALID_FORMAT, "email");
        if(!Validity.isValidPassword(password))
            throw new InputException("Invalid password format", InputException.ExceptionType.INVALID_FORMAT, "password");
    }

}