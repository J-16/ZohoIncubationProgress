package com.company;

import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyControllable;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.companiescustomer.controller.UserAuthenticationController;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;

//TODO : update user payment info, notify customer care downgrade plan, track issue

public class AutoAddDetails {

    {
        CompanyAuthenticationController controller = new CompanyAuthenticationController();
        //controller.register("c", "c", "123");
        controller.register("company1", "company1@gmail.com", "123456789");
        controller.register("company2", "company2@gmail.com", "123456789");

        CompanyControllable company = new CompanyController(new CompanyAuthenticationController().login("company1@gmail.com", "123456789"));
        company.addProduct("prod1",10,500);
        company.addProduct("prod2",0,500);
        company.addProduct("prod3",10,100);
        company.addSubscriptionPlan("prod1","Regular", SubscriptionPlan.SubscriptionType.MONTHLY,10);
        company.addSubscriptionPlan("prod1","Pro", SubscriptionPlan.SubscriptionType.MONTHLY,13);
        company.addSubscriptionPlan("prod1","Premium", SubscriptionPlan.SubscriptionType.MONTHLY,13);

        company = new CompanyController(new CompanyAuthenticationController().login("company1@gmail.com", "123456789"));
        company.addProduct("product1",10,500);
        company.addProduct("product2",0,500);
        company.addProduct("product3",10,100);


        company.addSubscriptionPlan("product1","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,12);
        company.addSubscriptionPlan("product1","Best Deal", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        company.addSubscriptionPlan("product1","Yearly", SubscriptionPlan.SubscriptionType.YEARLY,13);

        new CompanyDatabase().registerSubscriber("u","u");
        Subscriber subscriber = new CompanyDatabase().getSubscribersByEmail("u");
        subscriber.setPaymentDetails(new PaymentDetails(123L,12, LocalDate.now()));

        UserAuthenticationController subController = new UserAuthenticationController();
        subController.register("sub1","u@c.c","1234567890");

//        SubscriberController subscriberController = new SubscriberController("u","u","c");
//        subscriberController.subscribeProduct("prod1","Regular",null);

//        SubscriberDashboard subscriberDashboard = new SubscriberDashboard(subController.login("u","123"));
//        subscriberDashboard.displaySubscription("company1","prod1");

    }

}