package com.company.companiescustomer.view;

import com.company.companiescustomer.controller.CompanyListController;

import java.util.ArrayList;

public class CompanyListView {

    private CompanyListController companyListController = new CompanyListController();

    public void displayCompanies(){
        ArrayList<String> companies = companyListController.getCompanies();
        System.out.print("Company list : ");
        for(String company : companies){
            System.out.print( company + ", ");
        }
        System.out.println();
    }

}