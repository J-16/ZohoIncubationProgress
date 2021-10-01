package com.company.companiesuser.view;

import com.company.companiesuser.model.UserAccount;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.view.CompanyPortal;
import com.company.subscriptionmanagement.view.ProductView;
import com.company.companiesuser.controller.UserAuthenticationController;
import com.company.subscriptionmanagement.controllers.SubscriberController;

import java.util.Scanner;

public class UserPortal {

    private UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
    private SubscriberController subscriptionController;
    private String companyName;

    public void control(){
        new CompanyListView().displayCompanies();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter company name you want to login");
        companyName = sc.next();
        try{
            do{
                System.out.println("Login to continue");
                System.out.println("0.go back 1.Register 2.Login ");
                int option = sc.nextInt();
                switch (option){
                    case 0 :
                        return;
                    case 1 :
                        register();
                        break;
                    case 2 :
                        login();
                        break;
                    default :
                        System.out.println("Invalid option");
                }
            }while(true);
        }catch(DatabaseException | InputException e){
            System.out.println(e.getMessage());
        }
    }

    public void register(){
        System.out.println("Register your account");
        String name = Helper.getName();
        String email = Helper.getEmail();
        String password = Helper.getPassword();
        userAuthenticationController.register(name,email, password);
    }

    public void login(){
        try{
            System.out.println("Welcome back user");
            String email = CompanyPortal.Helper.getEmail();
            String password = CompanyPortal.Helper.getPassword();
            UserAccount userAccount = userAuthenticationController.login(email, password);
            loginFlow(email, userAccount.getName());
        }
        catch(DatabaseException e ){
            System.out.println(e.getMessage());
        }
    }

    public void loginFlow(String email, String name){
        subscriptionController = new SubscriberController(email, name, companyName);
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("1.Check Available Products 2.User DashBoard 0.Quit");
                int option = sc.nextInt();

                switch(option){
                    case 0 :
                        return;
                    case 1 :
                        new ProductView(subscriptionController, companyName).productsDetails();
                        break;
                    case 2 :
                        subscriptionController.dashBoard();
                        break;
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(true);
    }

    public static class Helper{
        static Scanner sc = new Scanner(System.in);
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
