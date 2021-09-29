package com.company.SubscriptionManagement.Controllers;

import com.company.SubscriptionManagement.Database.Database;
import com.company.SubscriptionManagement.Exception.InvalidException;
import com.company.SubscriptionManagement.Model.Company;

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

    public Company getCompanyByEmail(String companyName) {
        Company company =  Database.getCompanyByName(companyName);
        if(company == null)
            throw new InvalidException("No such company found");
        return company;
    }
}
