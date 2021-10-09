package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.Account;

public interface AuthenticationController{
    void register(String name, String email, String password);
    Account login(String email, String password);
}
