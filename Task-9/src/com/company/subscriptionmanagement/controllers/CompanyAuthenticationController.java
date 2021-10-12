package com.company.subscriptionmanagement.controllers;

import com.company.companiescustomer.dataBase.CustomerDatabase;
import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.service.AuthenticationService;

public class CompanyAuthenticationController{

    public enum LoginType{
        COMPANY,CUSTOMER;
    }

    protected AuthenticationService authenticationService;

    public CompanyAuthenticationController(LoginType type){
        if(type == LoginType.COMPANY)
            this.authenticationService = new AuthenticationService(new CompanyDatabase());
        else
            this.authenticationService = new AuthenticationService(new CustomerDatabase());
    }

    public void register(String name, String email, String password){
        authenticationService.register(name, email, password);
    }

    public Customer login(String email, String password){
        return authenticationService.login(email, password );
    }

}