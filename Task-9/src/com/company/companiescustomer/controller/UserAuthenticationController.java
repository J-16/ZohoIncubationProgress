package com.company.companiescustomer.controller;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.model.service.AuthenticationService;

public class UserAuthenticationController implements AuthenticationController{

    private AuthenticationService authenticationService = new AuthenticationService();

    public void register(String name, String email, String password){
        authenticationService.register(name, email, password, this);
    }

    public Customer login(String email, String password){
        return authenticationService.login(email, password, this);
    }

}