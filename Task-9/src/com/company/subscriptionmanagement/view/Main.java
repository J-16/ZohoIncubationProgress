package com.company.subscriptionmanagement.view;

import com.company.AutoAddDetails;
import com.company.companiesuser.view.UserPortal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{

    //TODO : update user payment info, notify customer care downgrade plan, track issue

    public static void main(String[] args){
        System.out.println("Sample company email : c password 123");
        System.out.println("Sample user email : u password 123");

        try {
            new AutoAddDetails();
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println();
                System.out.println("0.Quit 1.Company Portal 2.Select company to login");
                int option = -1;
                while(option < 0){
                    try{
                        option = scanner.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Invalid option");
                        System.out.println("0.Quit 1.Company Portal 2.Select company to login");
                        scanner.nextLine();
                    }
                }
                switch (option) {
                    case 0:
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
            } while (true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}