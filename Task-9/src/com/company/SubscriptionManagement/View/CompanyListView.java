package com.company.SubscriptionManagement.View;

import com.company.SubscriptionManagement.Controllers.CompanyListController;
import com.company.companiesuser.View.UserPortal;

import java.util.Scanner;
import java.util.ArrayList;

public class CompanyListView {

    public void displayCompanies(){
        Scanner sc = new Scanner(System.in);
        CompanyListController companyListController = new CompanyListController();
        ArrayList<String> companies = companyListController.getCompanies();
        for(String company : companies){
            System.out.println(company);
        }
        System.out.println("Enter company name");
        String companyName = sc.next();
        new UserPortal(companyName).control();
    }

}