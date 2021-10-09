package com.company.companiesuser.controller;

import com.company.companiesuser.model.User;
import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.model.service.AuthenticationService;

public class UserAuthenticationController implements AuthenticationController {

    private AuthenticationService authenticationService = new AuthenticationService();

    public void register(String name, String email, String password){
        authenticationService.register(name, email, password, this);
    }

    public User login(String email, String password){
        return (User) authenticationService.login(email, password, this);
    }

}