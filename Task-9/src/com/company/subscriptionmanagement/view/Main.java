package com.company.subscriptionmanagement.view;

import com.company.AutoAddDetails;
import com.company.companiescustomer.view.CompanyListView;
import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;
import com.company.subscriptionmanagement.controllers.SubscriberController;

public class Main{

    private static CompanyPortal portal;
    private static CompanyListView companyListView;

    public static void main(String[] args){
        new AutoAddDetails();
        do{
            System.out.println();
            int option = GetValues.getIntegerValue(0,"0.Quit 1.Company Portal 2.Select company");
            switch (option){
                case 0:
                    ScannerClass.closeScanner();
                    return;
                case 1:
                    portal = new CompanyPortal(CompanyAuthenticationController.LoginType.COMPANY);
                    new PortalControl().control(portal);
                    break;
                case 2:
                    companyListView = new CompanyListView();
                    companyListView.displayCompanies();
                    String companyName = GetValues.getString("Enter company name you want to login");

                    while(!SubscriberController.isValidCompany(companyName)) {
                        companyName = GetValues.getString("Invalid name, please enter a valid company name from the list above");
                    }
                    portal = new CompanyPortal(CompanyAuthenticationController.LoginType.CUSTOMER);
                    portal.setCompanyName(companyName);

                    new PortalControl().control(portal);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

}