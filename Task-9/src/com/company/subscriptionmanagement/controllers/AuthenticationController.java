package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.Users;

public interface AuthenticationController{
    void register(String name, String email, String password);
    Users login(String email, String password);
}
