package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.exception.InvalidException;
import com.company.subscriptionmanagement.model.ICompany;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyListController {

    public ArrayList<String> getCompanies(){
        ArrayList<String> companiesList = new ArrayList<>();
        HashMap<String, ICompany> companies = Database.getCompanies();
        for(ICompany company : companies.values()){
            companiesList.add(company.getAccount().getName());
        }
        return companiesList;
    }

    public ICompany getCompanyByEmail(String companyName) {
        ICompany company =  Database.getCompanyByName(companyName);
        if(company == null)
            throw new InvalidException("No such company found");
        return company;
    }
}
