package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyListController {

    private Database databaseService= new CompanyDatabase();

    public ArrayList<String> getCompanies(){
        ArrayList<String> companiesList = new ArrayList<>();
        HashMap<String, Company> companies = databaseService.getCompanies();
        for(Company company : companies.values()){
            companiesList.add(company.getAccount().getName());
        }
        return companiesList;
    }

}
