package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyDashboard {

    CompanyController companyController;

    public CompanyDashboard(CompanyController companyController){
        this.companyController = companyController;
    }

    public void control(){
        try{
            do{
                int option = GetValues.getIntegerValue(0, "0.Logout 1.Add product 2.Add Subscription Plan 3.Change Subscription Plan 4.Add Coupon");
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
                        updateSubscriptionPlan();
                        break;
                    case 4:
                        addCoupon();
                        break;
                    default :
                        System.out.println("Invalid option");
                        System.out.println("0.Logout 1.Add product 2.Add Subscription Plan 3.Change Subscription Plan 4.Add Coupon");
                }
            }while(true);
        }catch (DatabaseException e){
            System.out.println(e.getMessage());
        }
    }

    public void addProduct(){
        int trailDays = -1;
        double price  = -1;
        String name = null;
        System.out.println("Products Details : ");
        while(true){
            try{
                if(name == null) {
                    name = GetValues.getString("Enter Product Name");
                }
                if(trailDays < 0){
                    trailDays =GetValues.getIntegerValue(0,"Enter Trail days if any else 0");
                }
            if(price < 0)
                price = GetValues.getIntegerValue(0,"Enter Product price");
                companyController.addProduct(name, trailDays, price);
                System.out.println("Product added successfully");
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.EMPTY_EXCEPTION){
                    if(e.getField().equals("price"))
                        price = -1;
                }
                else if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("trailDays"))
                        trailDays = -1;
                }
            }
        }
    }

    public void addSubscriptionPlan() {
        int subType = 10;
        double discount = -1;
        String subscriptionName = null;
        String productName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        displayProducts();
        while(true){
            if(productName == null){
                productName = GetValues.getString("Enter Product name");
            }
            if(subscriptionName == null) {
                subscriptionName = GetValues.getString("Enter Subscription Name");
            }
            try {
                while (subType > 3){
                    subType = GetValues.getIntegerValue(1, "Choose subscription plan 1.Monthly 2.Quarterly 3.Yearly");
                    switch (subType) {
                        case 1:
                            subscriptionType = SubscriptionPlan.SubscriptionType.MONTHLY;
                            break;
                        case 2:
                            subscriptionType = SubscriptionPlan.SubscriptionType.QUARTERLY;
                            break;
                        case 3:
                            subscriptionType = SubscriptionPlan.SubscriptionType.YEARLY;
                        default:
                            System.out.print("Choose a valid subscription plan, ");
                    }
                }
                if(discount < 0) discount = GetValues.getDoubleValue(0, "Enter discount if any or 0");
                companyController.addSubscriptionPlan(productName, subscriptionName, subscriptionType, discount);
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
            }
        }
    }

    public void updateSubscriptionPlan(){
        displayProducts();
        String productName = null;
        String newSubscriptionName = null;
        String subscriptionName = null;
        SubscriptionPlan.SubscriptionType subscriptionType = null;
        displaySubscriptions(productName);
        int subType = 10;
        double discount = -1;
        while(true){
            try {
                if( productName == null){
                    productName = GetValues.getString("Select Product ");
                }
                if(subscriptionName == null){
                    subscriptionName = GetValues.getString("Select Subscription you want to change");
                }
                if(newSubscriptionName == null){
                    newSubscriptionName = GetValues.getString("Enter New Subscription Name if applicable or \"null\" for no changes");
                }
                System.out.println();
                while (subType > 4) {
                    subType = GetValues.getIntegerValue(0, "Choose new subscription plan 1.Monthly 2.Quarterly 3.Yearly or 0 for no changes");
                    switch (subType) {
                        case 1:
                            subscriptionType = SubscriptionPlan.SubscriptionType.MONTHLY;
                            break;
                        case 2:
                            subscriptionType = SubscriptionPlan.SubscriptionType.QUARTERLY;
                            break;
                        case 3:
                            subscriptionType = SubscriptionPlan.SubscriptionType.YEARLY;
                        default:
                            System.out.println("invalid option");
                    }
                }
                System.out.println();
                discount = GetValues.getDoubleValue(-1, "Enter discount if any or -1 if no changes");
                companyController.updateSubscriptionPlan(productName, subscriptionName, newSubscriptionName, subscriptionType, discount);
                return;
            }catch(InputException e){
                if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("discount"))
                        discount = -1;
                }
            }catch(DatabaseException e){
                if(e.getExceptionType() == DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION){
                    productName = null;
                    subscriptionName = null;
                }
            }
        }
    }

    public void addCoupon(){
        String productName = null;
        String coupon = null;
        double discount = -1;
        displayProducts();
        while(true){
            try{
                productName = GetValues.getString("Select Product");
                coupon = GetValues.getString("Enter coupon Code");
                System.out.println("Enter expiry date - yyyy-MM-dd");
                String date = GetValues.getDate("Enter Exp date yyyy-MM-dd");
                if(discount < 1)
                    discount = GetValues.getDoubleValue(1,"Enter discount in % example - 10");
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
        products.forEach(product -> {
            System.out.println( product.getProductName() );
        });
    }

    private void displaySubscriptions(String productName){
        ArrayList<SubscriptionPlan> subscriptionPlans;
        try{
            subscriptionPlans = companyController.getSubscriptionPlanByProduct(productName);
        }catch(DatabaseException | InputException e){
            System.out.println( e.getMessage() );
            return;
        }
        subscriptionPlans.forEach(subscriptionPlan -> {
            System.out.println( subscriptionPlan.getPlanName() );
        });
    }

}
