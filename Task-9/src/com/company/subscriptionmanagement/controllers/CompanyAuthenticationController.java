package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.ExceptionType;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.Company;

import java.util.regex.Pattern;

public class CompanyAuthenticationController {

    public void register(String name, String email, String password){
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if(Database.getCompanyByEmail(email) != null)
            throw new DatabaseException("Company already exists", ExceptionType.NOT_FOUND_EXCEPTION);
        Database.register(name, email, password);
    }

    public CompanyController login(String email, String password){
        Company companyAccount = Database.getCompanyByEmail(email);
        if(companyAccount == null)
            throw new DatabaseException("Username and password doesn't match", ExceptionType.NOT_FOUND_EXCEPTION);
        if( !companyAccount.getAccount().getPassword().equals(password) )
            throw new DatabaseException("Username and password doesn't match", ExceptionType.NOT_FOUND_EXCEPTION);
        return new CompanyController(companyAccount);
    }

    //TODO
    private boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[a-z1-9]+@[a-z]+.[a-z]+$");
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length() > 8;
    }

}
