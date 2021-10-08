package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.Company;

import java.util.regex.Pattern;

public class CompanyAuthenticationController {

    public void register(String name, String email, String password){
        if(name.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, name);
        if(email.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, email);
        if(password.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, password);
        if(Database.getCompanyByEmail(email) != null)
            throw new DatabaseException("Company already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
        Database.register(name, email, password);
    }

    public CompanyController login(String email, String password){
        if(email.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, email);
        if(password.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, password);
        Company companyAccount = Database.getCompanyByEmail(email);
        if(companyAccount == null)
            throw new DatabaseException("Username and password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        if( !companyAccount.getAccount().getPassword().equals(password) )
            throw new DatabaseException("Username and password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
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
