package com.company.subscriptionmanagement.view;

import com.company.AutoAddDetails;
import com.company.companiescustomer.controller.UserAuthenticationController;
import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;

public class Main{

    //TODO : why Account interface? use account interface // add new interface for company

    private static Portal portal;
    private static AuthenticationController authenticationController;

    public static void main(String[] args){
        new AutoAddDetails();
        do{
            System.out.println();
            int option = GetValues.getIntegerValue(0,"0.Quit 1.Company Portal 2.Select company to login");
            switch (option) {
                case 0:
                    ScannerClass.closeScanner();
                    return;
                case 1:
                    authenticationController = new CompanyAuthenticationController();
                    portal = new CompanyPortal(authenticationController);
                    new PortalControl().control(portal);
                    break;
                case 2:
                    authenticationController = new UserAuthenticationController();
                    portal = new CompanyPortal(authenticationController);
                    new PortalControl().control(portal);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

}