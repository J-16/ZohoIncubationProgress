package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;

public class CompanyPortal{

    private CompanyAuthenticationController companyAuthenticationController = new CompanyAuthenticationController();
    private String name = null , email = null, password = null;

    public void main(){
        System.out.println("Welcome to Company portal");
        int option = 10;
        while(option > 2){
            option = GetValues.getIntegerValue(0, "0.Previous Menu 1.Register 2.Login ");
            switch (option){
                case 0 :
                    return;
                case 1 :
                    registerFlow();
                case 2:
                    loginFlow();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void registerFlow() {
        while(true){
            try{
                register();
                companyAuthenticationController.register(name,email, password);
                System.out.println("Please login to continue");
                name = null;
                password = null;
                email = null;
                return;
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == DatabaseException.ExceptionType.EXISTS_EXCEPTION){
                    loginFlow();
                    return;
                }
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.INVALID_FORMAT){
                    if(e.getField().equals("email"))
                        email = null;
                    if(e.getField().equals("password"))
                        password = null;
                }
            }
        }
    }

    public void loginFlow(){
        while(true){
            try{
                login();
                new CompanyDashboard(new CompanyController(companyAuthenticationController.login(email, password))).control();
                email = null;
                password = null;
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.EMPTY_EXCEPTION){
                    if(e.getField().equals("email"))
                        email = null;
                    if(e.getField().equals("password"))
                        password = null;
                }
                if(e.getExceptionType() == InputException.ExceptionType.INVALID_FORMAT){
                    if(e.getField().equals("email"))
                        email = null;
                    if(e.getField().equals("password"))
                        password = null;
                }
            }catch(DatabaseException e){
                if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                    System.out.println("Username and password doesn't match");
                    return;
                }
            }
        }
    }

    private void register(){
        System.out.println("Register your company ");
        while(name == null){
            name = Helper.getName();
        }
        while(email == null){
            email = Helper.getEmail();
        }
        while(password == null){
            password = Helper.getPassword();
        }
    }

    private void login(){
        System.out.println("Welcome back ");
        do{
            email = Helper.getEmail();
        }while( email == null || email.isEmpty() );
        do{
            password = Helper.getPassword();
        }while( password == null || password.isEmpty() );
    }

    public static class Helper{

        public static String getName(){
            return GetValues.getString("Enter Name: ");
        }

        public static String getEmail(){
            return GetValues.getString("Enter Email: ");
        }

        public static String getPassword(){
            return  GetValues.getString("Enter Password: ");
        }

    }

}
