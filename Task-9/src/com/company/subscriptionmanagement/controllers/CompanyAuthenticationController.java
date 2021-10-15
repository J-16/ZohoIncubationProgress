package com.company.subscriptionmanagement.controllers;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.service.AuthenticationService;

public class CompanyAuthenticationController{

    protected AuthenticationService authenticationService;

    public CompanyAuthenticationController(){
            this.authenticationService = new AuthenticationService();
    }

    public void register(String name, String email, String password, CompanyDatabase.UserType userType){
        authenticationService.register(name, email, password, userType);
    }

    public Customer login(String email, String password,CompanyDatabase.UserType userType){
        return authenticationService.login(email, password, userType);
    }

}