package com.company.subscriptionmanagement.view;


import com.company.subscriptionmanagement.controllers.*;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.Company;

public class CompanyPortal{

    protected String name = null;
    protected String email = null;
    protected String password = null;

    private AuthenticationController authenticationController;
    private Dashboard dashboard;
    private ProductView productView;
    private String companyName;

    public CompanyPortal(){
        this.authenticationController = new AuthenticationController();
    }

    public void registerFlow(){
        System.out.println("Register");
        while(true){
            try{
                new Helper().register();
                if(companyName == null){
                    authenticationController.register(name,email, password, CompanyDatabase.UserType.COMPANY);
                }
                else{
                    authenticationController.register(name,email, password, CompanyDatabase.UserType.CUSTOMER);
                }
                DisplayMessage.successMessage("Registered successfully, Please login to continue");
                Thread.sleep(1000);
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
                    if(e.getField().equals("email")){
                        email = null;
                        password = null;
                    }
                    if(e.getField().equals("password"))
                        password = null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loginFlow(){
        while(true){
            try{
                new Helper().login();
                if(companyName == null){
                    dashboard = new CompanyDashboard(new CompanyController( (Company) authenticationController.login(email, password,CompanyDatabase.UserType.COMPANY)));
                    dashboard.control();
                }else{
                    authenticationController.login(email, password, CompanyDatabase.UserType.CUSTOMER);
                    loggedIn(companyName);
                }
                email = null;
                password = null;
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.EMPTY_EXCEPTION){
                    if(e.getField().equals("email")){
                        email = null;
                        password = null;
                    }
                    if(e.getField().equals("password"))
                        password = null;
                }
                if(e.getExceptionType() == InputException.ExceptionType.INVALID_FORMAT){
                    if(e.getField().equals("email")){
                        email = null;
                        password = null;
                    }
                    if(e.getField().equals("password"))
                        password = null;
                }
            }catch(DatabaseException e){
                if(e.getExceptionType().equals(DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION)){
                    System.out.println("Username and password doesn't match");
                    email = null;
                    password = null;
                    return;
                }
            }
        }
    }

    private void loggedIn(String companyName){
        SubscriberController subscriptionController = new SubscriberController(email, name, companyName);
        DisplayMessage.successMessage("Welcome back ");
        do{
            int option = -1;
            while(option < 0 || option > 2){
                option = GetValues.getIntegerValue("0.Logout 1.Available Products 2.User DashBoard", "Choose a valid option");
            }
            switch(option){
                case 0 :
                    return;
                case 1 :
                    productView = new ProductView(subscriptionController, companyName);
                    productView.productsDetails();
                    break;
                case 2 :
                    System.out.println("Welcome to subscription management System subscriber portal");
                    subscriptionController.dashBoard();
                    break;
            }
        }while(true);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public class Helper{

        public void register(){

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
            while(email == null){
                email = getEmail();
            }
            while(password == null){
                password = getPassword();
            };
        }

        public String getName(){
            return GetValues.getString("Enter Name: ");
        }

        public String getEmail(){
            return GetValues.getString("Enter Email:  (must be small letters only) ");
        }

        public String getPassword(){
            return  GetValues.getString("Enter Password: (must be 8 characters long) ");
        }

    }

}