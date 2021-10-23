package com.company.companiescustomer.controller;

import com.company.subscriptionmanagement.database.CurrentDatabase;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.model.Company;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyListController{

    private UserDB database;

    public CompanyListController(){
        this.database = CurrentDatabase.getUserDatabase();
    }

    public ArrayList<String> getCompanies(){
        ArrayList<String> companiesList = new ArrayList<>();
        HashMap<String, Company> companies = database.getCompanies();
        for(Company company : companies.values()){
            companiesList.add(company.getName());
        }
        return companiesList;
    }

}
