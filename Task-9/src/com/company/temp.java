package com.company;

import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.controllers.ProductController;
import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.database.DataStructures.UserDS;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.Product;
import com.company.subscriptionmanagement.view.ProductView;

public class temp{

    public static void main(String ...args){

        new AutoAddDetails();
//        SubscriberController subscriberController = new SubscriberController("u@c.c","u@c.c","company1");
//        subscriberController.subscribeProduct("prod1","Regular",null);
//        ProductView productView = new ProductView(subscriberController,"company1");
//        productView.displaySubscription("prod1");
//        subscriberController.dashBoard();

        new AuthenticationController().register("u","u@c.c","123456789", UserDB.UserType.COMPANY);
        Company company = new AuthenticationController().login("u@c.c","123456789", UserDB.UserType.COMPANY);
        ProductController productController = new ProductController(company);

    }

}