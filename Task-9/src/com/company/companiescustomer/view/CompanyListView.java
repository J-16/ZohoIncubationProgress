package com.company.companiescustomer.view;

import com.company.subscriptionmanagement.controllers.CompanyListControllable;
import com.company.subscriptionmanagement.controllers.CompanyListController;

import java.util.ArrayList;

public class CompanyListView implements CompanyListViewable {

    private CompanyListControllable companyListController = new CompanyListController();

    public void displayCompanies(){
        ArrayList<String> companies = companyListController.getCompanies();
        System.out.print("Company list : ");
        for(String company : companies){
            System.out.print( company + ", ");
        }
        System.out.println();
    }

}