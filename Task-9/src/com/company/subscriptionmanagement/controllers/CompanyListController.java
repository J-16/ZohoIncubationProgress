package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.model.Company;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyListController {

    public ArrayList<String> getCompanies(){
        ArrayList<String> companiesList = new ArrayList<>();
        HashMap<String, Company> companies = Database.getCompanies();
        for(Company company : companies.values()){
            companiesList.add(company.getAccount().getName());
        }
        return companiesList;
    }

}
