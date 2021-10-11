package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.service.AuthenticationService;

public class CompanyAuthenticationController implements AuthenticationController{

    private AuthenticationService authenticationService;

    public void register(String name, String email, String password){
        authenticationService = new AuthenticationService();
        authenticationService.register(name, email, password, this);
    }

    public Company login(String email, String password){
        authenticationService = new AuthenticationService();
        return (Company) authenticationService.login(email, password, this);
    }

}