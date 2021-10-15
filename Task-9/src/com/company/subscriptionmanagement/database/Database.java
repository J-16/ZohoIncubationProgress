package com.company.subscriptionmanagement.database;

import com.company.companiescustomer.model.Customer;

public interface Database{
    void register(String name, String email, String password);
    <C extends Customer> C getUserByEmail(String email);
}