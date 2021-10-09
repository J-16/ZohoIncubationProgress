package com.company.companiesuser.view;

import com.company.subscriptionmanagement.controllers.CompanyListController;

import java.util.ArrayList;

public class CompanyListView {

    public void displayCompanies(){
        CompanyListController companyListController = new CompanyListController();
        ArrayList<String> companies = companyListController.getCompanies();
        System.out.print("Company list : ");
        for(String company : companies){
            System.out.print( company + ", ");
        }
        System.out.println();
    }

}