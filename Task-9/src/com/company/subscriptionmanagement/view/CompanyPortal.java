package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;

import java.util.Scanner;

public class CompanyPortal{

    private CompanyAuthenticationController companyAuthenticationController = new CompanyAuthenticationController();
    private CompanyController companyController;
    private String name =null , email = null, password = null;

    public void register(){
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
        System.out.println("Welcome to Company portal");
        int option = 10;
        while(option > 2){
            option = GetValues.getIntegerValue(0, "0.Previous Menu 1.Register 2.Login ");
            switch (option){
                case 0 :
                    return;
                case 1 :
                    try{
                        register();
                        System.out.println("Please login to continue");
                    }catch(DatabaseException e){
                        System.out.println(e.getMessage());
                        if(e.getExceptionType().equals(DatabaseException.ExceptionType.EXISTS_EXCEPTION)){
                            loginFlow();
                            break;
                        }
                    }finally{
                        name = null;
                        password = null;
                        email = null;
                    }
                case 2:
                    try{
                        loginFlow();
                        break;
                    }
                    catch(InputException e){
                        System.out.println(e.getMessage());
                        if(e.getExceptionType().equals(InputException.ExceptionType.EMPTY_EXCEPTION)){
                            if(e.getField().equals("email"))
                                email = null;
                            else
                                password = null;
                        }
                    }
                    catch(DatabaseException e){
                        if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                            System.out.println("Username and password doesn't match");
                            loginFlow();
                            break;
                        }
                    }
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void loginFlow(){
        login();
        new CompanyDashboard(companyController).control();
    }

    public static class Helper{

        private static Scanner sc = new Scanner(System.in);

        public static String getName(){
            System.out.println("Enter Name: ");
            return sc.next();
        }

        public static String getEmail(){
            System.out.println("Enter Email: ");
            return sc.next();
        }

        public static String getPassword(){
            System.out.println("Enter Password: ");
            return  sc.next();
        }

    }

}
