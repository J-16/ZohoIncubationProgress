package com.company.SubscriptionManagement.View;

import com.company.SubscriptionManagement.Controllers.CompanyAuthenticationController;
import com.company.SubscriptionManagement.Controllers.CompanyController;
import com.company.SubscriptionManagement.Exception.InvalidException;

import java.util.Scanner;

public class CompanyPortal {

    private CompanyAuthenticationController companyAuthenticationController = new CompanyAuthenticationController();
    private CompanyController companyController;

    public void register(){
        String name =null , email = null, password = null;
        System.out.println("Register your account ");

        while(name == null){
            name = Helper.getName();
        }
        while(email == null){
            email = Helper.getEmail();
        }
        while(password == null){
            password = Helper.getPassword();
        }
        companyAuthenticationController.register(name,email, password);
    }

    public void login(){
        String email = null, password = null;
        System.out.println("Welcome back ");
        do{
           email = Helper.getEmail();
        }while( email == null || email.isEmpty() );
        do{
            password = Helper.getPassword();
        }while( password == null || password.isEmpty() );
        companyController = companyAuthenticationController.login(email, password);
    }

    public void main(){
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Welcome to Company portal");
            System.out.println("0.go back 1.Register 2.Login ");
            int option = sc.nextInt();
            switch (option){
                case 0 :
                    return;
                case 1 :
                    register();
                case 2:
                    loginFlow();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

    public void loginFlow(){
        try{
            login();
            new CompanyDashboard(companyController).control();
        }catch(InvalidException e){
            System.out.println(e.getMessage());
        }
    }

    public static class Helper{

        private static Scanner sc = new Scanner(System.in);

        public static String getName(){
            System.out.println("Enter Name:");
            return sc.next();
        }

        public static String getEmail(){
            System.out.println("Enter Email:");
            return sc.next();
        }

        public static String getPassword(){
            System.out.println("Enter Password:");
            return sc.next();
        }
    }

}
