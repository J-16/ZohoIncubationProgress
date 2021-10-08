package com.company.subscriptionmanagement.view;

import com.company.AutoAddDetails;
import com.company.companiesuser.view.UserPortal;

public class Main{

    //TODO : update user payment info, notify customer care downgrade plan, track issue

    public static void main(String[] args){
        System.out.println("Sample company email : c password 123");
        System.out.println("Sample user email : u password 123");
        new AutoAddDetails();
        do{
            System.out.println();
            int option = GetValues.getIntegerValue(0,"0.Quit 1.Company Portal 2.Select company to login");
            switch (option) {
                case 0:
                    ScannerClass.closeScanner();
                    return;
                case 1:
                    new CompanyPortal().main();
                    break;
                case 2:
                    new UserPortal().control();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while (true);
    }

}