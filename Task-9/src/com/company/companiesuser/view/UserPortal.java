package com.company.companiesuser.view;

import com.company.companiesuser.model.UserAccount;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.view.CompanyPortal;
import com.company.subscriptionmanagement.view.GetValues;
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

        while( !SubscriberController.isValidCompany(companyName) ){
            System.out.println("Invalid name, please enter a valid company name from the list above");
            companyName = sc.next();
        }
        do{
            System.out.println("Login to continue");
            int option = GetValues.getIntegerValue(0,"0.Previous Menu 1.Register 2.Login ");
            switch(option){
                case 0 :
                    return;
                    case 1:
                        try{
                            register();
                        }catch(DatabaseException e){
                            System.out.println(e.getMessage());
                            if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                                System.out.println("Username and password doesn't match");
                                login();
                            }
                        }
                    case 2:
                        try{
                            login();
                            break;
                        }catch(DatabaseException e){
                            if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                                System.out.println("Username and password doesn't match");
                                login();
                            }
                        }
                    default:
                        System.out.println("Invalid option");
                    }
                }while(true);
    }

    public void register(){
        System.out.println("Register your account");
        String name = CompanyPortal.Helper.getName();
        String email = CompanyPortal.Helper.getEmail();
        String password = CompanyPortal.Helper.getPassword();
        userAuthenticationController.register(name,email, password);
    }

    public void login(){
        System.out.println("Welcome back user");
        String email = CompanyPortal.Helper.getEmail();
        String password = CompanyPortal.Helper.getPassword();
        UserAccount userAccount = userAuthenticationController.login(email, password);
        loginFlow(email, userAccount.getName());
    }

    public void loginFlow(String email, String name){
        subscriptionController = new SubscriberController(email, name, companyName);
        do{
            try{
                int option = GetValues.getIntegerValue(0,"0.Logout 1.Check Available Products 2.User DashBoard");
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

}
