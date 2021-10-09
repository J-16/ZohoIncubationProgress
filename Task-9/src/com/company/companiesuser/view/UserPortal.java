package com.company.companiesuser.view;

import com.company.companiesuser.model.User;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.view.CompanyPortal;
import com.company.subscriptionmanagement.view.GetValues;
import com.company.subscriptionmanagement.view.ProductView;
import com.company.companiesuser.controller.UserAuthenticationController;
import com.company.subscriptionmanagement.controllers.SubscriberController;

public class UserPortal {

    private UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
    private SubscriberController subscriptionController;
    private String companyName;

    public void control(){
        new CompanyListView().displayCompanies();
        companyName = GetValues.getString("Enter company name you want to login");

        while(!SubscriberController.isValidCompany(companyName)){
            companyName = GetValues.getString("Invalid name, please enter a valid company name from the list above");
        }

        do{
            System.out.println("Login to continue");
            int option = GetValues.getIntegerValue(0,"0.Previous Menu 1.Register 2.Login ");
            switch(option){
                case 0 :
                    return;
                    case 1:
                        register();
                    case 2:
                        login();
                        break;
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
        while(true){
            try{
                userAuthenticationController.register(name,email, password);
                return;
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                    System.out.println("Username and password doesn't match");
                    login();
                }
            }
        }
    }

    public void login(){
        System.out.println("Welcome back user");
        String email = null;
        String password = null;
        while(true){
            try{
                if(email == null)
                    email = CompanyPortal.Helper.getEmail();
                if(password == null)
                    password = CompanyPortal.Helper.getPassword();
                User userAccount = userAuthenticationController.login(email, password);
                loginFlow(email, userAccount.getAccount().getName());
                return;
            }catch(DatabaseException e){
                if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                    System.out.println("Username and password doesn't match");
                    email = null;
                    password = null;
                }
            }
        }
    }

    public void loginFlow(String email, String name){
        subscriptionController = new SubscriberController(email, name, companyName);
        do{
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
        }while(true);
    }

}
