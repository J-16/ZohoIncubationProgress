package com.company.SubscriptionManagement.View;

import com.company.AutoAddDetails;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        try {
            new AutoAddDetails();
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println();
                System.out.println("0.Quit 1.Company Portal 2.View Companies");
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        new CompanyPortal().main();
                        break;
                    case 2:
                        new CompanyListView().displayCompanies();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } while (true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}