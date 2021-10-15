package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyDashboard implements Dashboard{

    CompanyController companyController;

    public CompanyDashboard(CompanyController companyController){
        this.companyController = companyController;
    }

    public void control(){
            do{
                try{
                    int option = -1;
                    while(option < 0 || option > 4){
                         option = GetValues.getIntegerValue("0.Logout 1.Add product 2.Add Subscription Plan 3.Change Subscription Plan 4.Add Coupon","Select a valid option");
                    }
                    switch(option){
                        case 0:
                            return;
                        case 1:
                            addProduct();
                            break;
                        case 2:
                            addSubscriptionPlan();
                            break;
                        case 3:
                            changeSubscriptionPlan();
                            break;
                        case 4:
                            addCoupon();
                            break;
                        default :
                            System.out.println("Invalid option");
                    }
                }catch (DatabaseException e){
                    System.out.println(e.getMessage());
                }
            }while(true);
    }

    private void addProduct(){
        int trailDays = -1;
        double price  = -1;
        String name = null;
        try{
            displayProducts();
        }catch(DatabaseException e){}
        System.out.println("---------------Products Details---------------");
        while(true){
            try{
                if(name == null) {
                    name = GetValues.getString("Enter new product name");
                }
                while(trailDays < 0){
                    trailDays = GetValues.getIntegerValue("Enter Trail days if any else 0", "Trail Days cannot be negative");
                }
            while(price < 0){
                price = GetValues.getIntegerValue("Enter Product price","price cannot be negative");
            }
                companyController.addProduct(name, trailDays, price);
                System.out.println("Product added successfully");
                displayProducts();
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.EMPTY_EXCEPTION){
                    if(e.getField().equals("price")) {
                        price = -1;
                    }
                }
                else if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("trailDays")) {
                        trailDays = -1;
                    }
                }
            }
        }
    }

    private void addSubscriptionPlan() {
        int subType = 10;
        double discount = -1;
        String subscriptionName = null;
        String productName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        displayProducts();
        while(true){
            if(productName == null){
                productName = GetValues.getString("Enter Product name");
                try{
                    displaySubscriptions(productName);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
            if(subscriptionName == null) {
                subscriptionName = GetValues.getString("Enter new subscription plan name");
            }
            try {
                while(subType < 0 || subType > 3){
                    subType = GetValues.getIntegerValue("Choose a subscription plan 1.Monthly 2.Quarterly 3.Yearly","Choose a valid subscription plan");
                    switch (subType) {
                        case 1:
                            subscriptionType = SubscriptionPlan.SubscriptionType.MONTHLY;
                            break;
                        case 2:
                            subscriptionType = SubscriptionPlan.SubscriptionType.QUARTERLY;
                            break;
                        case 3:
                            subscriptionType = SubscriptionPlan.SubscriptionType.YEARLY;
                    }
                }
                while(discount < 0) discount = GetValues.getDoubleValue("Enter discount if any or 0", "discount cannot be negative value");
                companyController.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
                System.out.println("new subscription plan added");
                displaySubscriptions(productName);
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.EMPTY_EXCEPTION){
                    if(e.getField().equals("subscriptionName"))
                        subscriptionName = null;
                }
                else if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("discount"))
                        discount = -1;
                }
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
                productName = null;
            }
        }
    }

    private void changeSubscriptionPlan(){
        displayProducts();
        String productName = null;
        String newSubscriptionName = null;
        String subscriptionName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        int subType = 5;
        double discount = -1;
        while(true){
            if( productName == null){
                productName = GetValues.getString("Enter Product name ");
                try{
                    displaySubscriptions(productName);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
            if(subscriptionName == null){
                subscriptionName = GetValues.getString("Select Subscription you want to change");
            }
            if(newSubscriptionName == null){
                newSubscriptionName = GetValues.getString("Enter New Subscription Name if applicable or \"null\" for no changes");
            }
            try{
                while(subType < 0 || subType > 3){
                    subType = GetValues.getIntegerValue("Choose new subscription plan 1.Monthly 2.Quarterly 3.Yearly or 0 for no changes", "Choose a valid option");
                    switch (subType) {
                        case 1:
                            subscriptionType = SubscriptionPlan.SubscriptionType.MONTHLY;
                            break;
                        case 2:
                            subscriptionType = SubscriptionPlan.SubscriptionType.QUARTERLY;
                            break;
                        case 3:
                            subscriptionType = SubscriptionPlan.SubscriptionType.YEARLY;
                            break;
                    }
                }
                System.out.println();
                while(discount < 0)
                    discount = GetValues.getDoubleValue("Enter discount if any or 0 if no changes", "discount cannot be negative");
                companyController.updateSubscriptionPlan(productName, subscriptionName, newSubscriptionName, subscriptionType, discount);
                System.out.println("Changed Successfully");
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("discount"))
                        discount = -1;
                }
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION){
                    if(e.getField().equals("subscriptionName"))
                        subscriptionName = null;
                    if(e.getField().equals("productName"))
                        productName = null;
                }
            }
        }
    }

    private void addCoupon(){
        String productName = null;
        String coupon = null;
        double discount = -1;
        displayProducts();
        while(true){
            try{
                productName = GetValues.getString("Select Product");
                coupon = GetValues.getString("Enter coupon Code");
                String date = GetValues.getDate("Enter Expiry date yyyy-MM-dd");
                while(discount < 0)
                    discount = GetValues.getDoubleValue("Enter discount in % example - 10", "Discount cannot be negative value");
                companyController.addCoupon(productName, coupon, LocalDate.parse(date), discount);
                return;
            }catch(InputException e){
                if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("discount"))
                        discount = -1;
                }
            }
        }
    }

    private void displayProducts(){
        ArrayList<Product> products = companyController.getProducts();
        System.out.println("----------Available Products----------");
        System.out.println( "\u001B[34m" + "Product               Price         Trail" + "\u001B[0m");
        products.forEach(product -> {
            System.out.println( product.getProductName() + "                 " +  product.getPrice()  + "          " +  product.getTrailDays());
        });
        System.out.println("---------------------------");
    }

    private void displaySubscriptions(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans = companyController.getSubscriptionPlanByProduct(productName);
        System.out.println("----------Available Subscription Plans----------");
        System.out.println("Actual price for the product : " + companyController.getProductByName(productName).getPrice());
        System.out.println( "\u001B[34m" + "Plan                 Type              Price" + "\u001B[0m");
        subscriptionPlans.forEach(subscriptionPlan -> {
            System.out.println( subscriptionPlan.getPlanName() + "                " + subscriptionPlan.getSubscriptionType() + "          " + subscriptionPlan.getPrice() );
        });
        System.out.println("--------------------------------------");
    }

}
