package com.company.View;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        new AutoAddDetails();
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println();
            System.out.println("0.Quit 1.Company Portal 2.Subscriber Portal ");
            int option = scanner.nextInt();
            switch(option){
                case 0 :
                    return;
                case 1 :
                    new CompanyView().main();
                    break;
                case 2 :
                    //TODO : implement
                default :
                    System.out.println("Invalid option");
            }
        }while(true);
    }
}