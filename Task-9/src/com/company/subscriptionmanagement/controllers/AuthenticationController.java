package com.company.subscriptionmanagement.controllers;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.service.AuthenticationService;
import com.company.subscriptionmanagement.model.service.Validity;

public class AuthenticationController {

    protected AuthenticationService authenticationService;

    public AuthenticationController(){
            this.authenticationService = new AuthenticationService();
    }

    public void register(String name, String email, String password, CompanyDatabase.UserType userType){
        Validity.emptyCheck("name",name);
        Validity.emptyCheck("email", email,"password", password);
        validity(email, password);
        authenticationService.register(name, email, password, userType);
    }

    public Customer login(String email, String password, CompanyDatabase.UserType userType){
        Validity.emptyCheck("email",email, "password", password);
        validity(email, password);
        return authenticationService.login(email, password, userType);
    }

    private void validity(String email, String password){
        if(!Validity.isValidEmail(email))
            throw new InputException("Invalid email format", InputException.ExceptionType.INVALID_FORMAT, "email");
        if(!Validity.isValidPassword(password))
            throw new InputException("Invalid password format", InputException.ExceptionType.INVALID_FORMAT, "password");
    }

}