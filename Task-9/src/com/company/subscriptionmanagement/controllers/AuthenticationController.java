package com.company.subscriptionmanagement.controllers;

import com.company.companiescustomer.model.Customer;

public interface AuthenticationController{
    void register(String name, String email, String password);
    Customer login(String email, String password);
}