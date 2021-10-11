package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.Company;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyListController {

    private CompanyDatabase database= new CompanyDatabase();

    public ArrayList<String> getCompanies(){
        ArrayList<String> companiesList = new ArrayList<>();
        HashMap<String, Company> companies = database.getCompanies();
        for(Company company : companies.values()){
            companiesList.add(company.getAccount().getName());
        }
        return companiesList;
    }

}
