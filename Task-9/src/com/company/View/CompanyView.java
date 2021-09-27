package com.company.View;

import com.company.Controllers.CompanyAuthenticationController;
import com.company.Exception.DatabaseException;
import com.company.Model.CompanyAccount;

import java.util.Scanner;

public class CompanyView {

    private Scanner sc = new Scanner(System.in);
    private CompanyAuthenticationController companyAuthenticationController = new CompanyAuthenticationController();
    private CompanyAccount company;

    private void register(){
        String name =null , email = null, password = null;
        System.out.println("Register your account ");

        while(name == null){
            name = getName();
        }
        while(email == null){
            email = getEmail();
        }
        while(password == null){
            password = getPassword();
        }
    }

    private void login(){
        String email = null, password = null;
        System.out.println("Welcome back ");
        do{
           email = getEmail();
        }while( email == null || email.isEmpty() );
        do{
            password = getPassword();
        }while( password == null || password.isEmpty() );
        company = companyAuthenticationController.login(email, password);
        System.out.println( company.getAccount().getName() );
    }

    public void main(){
        new AutoAddDetails();
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

    private void loginFlow(){
        try{
            login();
            new CompanyFunctionalities(company).control();
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    private String getName(){
        System.out.println("Enter Name:");
        return sc.next();
    }

    private String getEmail(){
        System.out.println("Enter Email:");
        return sc.next();
    }

    private String getPassword(){
        System.out.println("Enter Password:");
        return sc.next();
    }

}
