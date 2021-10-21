package com.company.subscriptionmanagement.view;

import com.company.AutoAddDetails;
import com.company.companiescustomer.view.CompanyListView;
import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.database.CompanyDatabase;

public class Main{

    private static CompanyPortal portal;
    private static CompanyListView companyListView = new CompanyListView();

    public static void main(String[] args){
        new AutoAddDetails();
        do{
            System.out.println();
            int option = -1;
            while(option < 0 || option > 2){
                option = GetValues.getIntegerValue("0.Quit 1.Company Portal 2.Select company", "Choose a valid option");
            }
            switch (option){
                case 0:
                    DisplayMessage.successMessage("Have a good day");
                    ScannerClass.closeScanner();
                    return;
                case 1:
                    portal = new CompanyPortal(CompanyDatabase.UserType.COMPANY);
                    System.out.println("Welcome to the Subscription management System");
                    new PortalControl().control(portal);
                    break;
                case 2:
                    portal = new CompanyPortal(CompanyDatabase.UserType.CUSTOMER);
                    companyListView.displayCompanies();
                    String companyName = GetValues.getString("Enter company name you want to login");

                    while(!SubscriberController.isValidCompany(companyName)) {
                        companyName = GetValues.getString("Invalid name, please enter a valid company name from the list above");
                    }
                    portal.setCompanyName(companyName);
                    new PortalControl().control(portal);
                    break;
            }
        }while(true);
    }
}