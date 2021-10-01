package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.UserException;
import com.company.subscriptionmanagement.model.Company;

public class CompanyAuthenticationController {

    public void register(String name, String email, String password){
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        if(Database.getCompanyByEmail(email) != null)
            throw new UserException("Company already exists");
        Database.register(name, email, password);
    }

    public CompanyController login(String email, String password){
        Company companyAccount = Database.getCompanyByEmail(email);
        if( companyAccount == null )
            throw new UserException("No such company found");
        if( !companyAccount.getAccount().getPassword().equals(password) )
            throw new UserException("Password doesn't match");
        return new CompanyController(companyAccount);
    }

}
