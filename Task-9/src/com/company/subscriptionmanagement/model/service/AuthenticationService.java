package com.company.subscriptionmanagement.model.service;

import com.company.companiescustomer.controller.UserAuthenticationController;
import com.company.companiescustomer.dataBase.UserDatabase;
import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.Company;

import java.util.regex.Pattern;

public class AuthenticationService{

    private CompanyDatabase database = new CompanyDatabase();
    private UserDatabase userDatabase = new UserDatabase();

    public void register(String name, String email, String password, AuthenticationController authenticationController){
        empty(name, email, password);
        validity(email, password);
        if(authenticationController instanceof UserAuthenticationController){
            if(userDatabase.getUserByEmail(email) != null)
                throw new DatabaseException("User already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
            userDatabase.registerUser(name, email, password);
            return;
        }
        if(CompanyDatabase.getCompanyByEmail(email) != null)
            throw new DatabaseException("Company already exists", DatabaseException.ExceptionType.EXISTS_EXCEPTION);
        database.register(name, email, password);
    }

    public Customer login(String email, String password, AuthenticationController authenticationController){
        empty("name", email, password);
        validity(email, password);
        if(authenticationController instanceof UserAuthenticationController){
            Customer userAccount = userDatabase.getUserByEmail(email);
            if( userAccount == null )
                throw new DatabaseException("No such Subscriber found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
            if( !userAccount.getAccount().getPassword().equals(password) )
                throw new DatabaseException("Password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
            return userAccount;

        }
        Company companyAccount = CompanyDatabase.getCompanyByEmail(email);
        if(companyAccount == null)
            throw new DatabaseException("Username and password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        if(!companyAccount.getAccount().getPassword().equals(password) )
            throw new DatabaseException("Username and password doesn't match", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        return companyAccount;
    }

    private void empty(String name, String email, String password){
        if(name.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, "name");
        if(email.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, "email");
        if(password.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, "password");
    }

    public void validity(String email, String password){
        if(!isValidEmail(email))
            throw new InputException("Invalid email format", InputException.ExceptionType.INVALID_FORMAT, "email");
        if(!isValidPassword(password))
            throw new InputException("Invalid password format", InputException.ExceptionType.INVALID_FORMAT, "password");
    }

    private boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[a-z1-9]+@[a-z]+.[a-z]+$");
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length() > 8;
    }

}
