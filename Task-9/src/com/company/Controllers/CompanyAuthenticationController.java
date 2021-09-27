package com.company.Controllers;

import com.company.Database.Database;
import com.company.Exception.DatabaseException;
import com.company.Exception.InputException;
import com.company.Model.CompanyAccount;

public class CompanyAuthenticationController {

    public void register(String name, String email, String password){
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() )
            throw new InputException("Empty fields are not allowed");
        Database.register(name, email, password);
    }

    public CompanyAccount login(String email, String password){
        CompanyAccount companyAccount = Database.getCompanyByEmail(email);
        if( companyAccount == null )
            throw new DatabaseException("No such company found");
        if( !companyAccount.getAccount().getPassword().equals(password) )
            throw new DatabaseException("Password doesn't match");
        return companyAccount;
    }

}
