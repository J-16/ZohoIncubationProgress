package com.company.companiesuser.View;

import com.company.SubscriptionManagement.View.CompanyPortal;
import com.company.SubscriptionManagement.View.ProductView;
import com.company.companiesuser.Controller.UserAuthenticationController;
import com.company.SubscriptionManagement.Controllers.SubscriptionController;

import java.util.Scanner;

public class UserPortal {

    private UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
    private SubscriptionController subscriptionController;
    private String companyName;

    public UserPortal(String companyName){
        this.companyName = companyName;
    }

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
        userAuthenticationController.register(name,email, password);
    }

    public void login(){
        try{
        String email = null, password = null;
        System.out.println("Welcome back ");
        do{
            email = CompanyPortal.Helper.getEmail();
        }while( email == null || email.isEmpty() );
        do{
            password = CompanyPortal.Helper.getPassword();
        }while( password == null || password.isEmpty() );

            subscriptionController = userAuthenticationController.login(email, password);
            subscriptionController.setCompany(companyName);
            loginFlow();
        }
        catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }

    public void loginFlow(){
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("1.Check Products 2.User DashBoard 0.Quit");
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

    public void control(){
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Login to continue");
            System.out.println("0.go back 1.Register 2.Login ");
            int option = sc.nextInt();
            switch (option){
                case 0 :
                    return;
                case 1 :
                    register();
                case 2 :
                    login();
                    break;
                default :
                    System.out.println("Invalid option");
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
