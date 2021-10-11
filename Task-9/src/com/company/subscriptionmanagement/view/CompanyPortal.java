package com.company.subscriptionmanagement.view;

import com.company.companiescustomer.view.CompanyListView;
import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.Company;

public class CompanyPortal{

    protected String name = null;
    protected String email = null;
    protected String password = null;

    AuthenticationController authenticationController;

    public CompanyPortal(AuthenticationController authenticationController){
        this.authenticationController = authenticationController;
    }

    public void registerFlow(){
        while(true){
            try{
                new Helper().register();
                authenticationController.register(name,email, password);
                System.out.println("Please login to continue");
                name = null;
                password = null;
                email = null;
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
                new Helper().login();
                if(authenticationController instanceof CompanyAuthenticationController){
                    new CompanyDashboard(new CompanyController((Company) authenticationController.login(email, password))).control();
                }else{
                    new CompanyListView().displayCompanies();
                    String companyName = GetValues.getString("Enter company name you want to login");

                    while(!SubscriberController.isValidCompany(companyName))
                        companyName = GetValues.getString("Invalid name, please enter a valid company name from the list above");
                    authenticationController.login(email, password);
                    loggedIn(companyName);
                }
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

    private void loggedIn(String companyName){
        SubscriberController subscriptionController = new SubscriberController(email, name, companyName);
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

    public class Helper{

        public void register(){
            System.out.println("Register your company ");
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

        public void login(){
            System.out.println("Welcome back ");
            do{
                email = getEmail();
            }while(email == null);
            do{
                password = getPassword();
            }while(password == null);
        }

        public String getName(){
            return GetValues.getString("Enter Name: ");
        }

        public String getEmail(){
            return GetValues.getString("Enter Email: ");
        }

        public String getPassword(){
            return  GetValues.getString("Enter Password: ");
        }

    }

}