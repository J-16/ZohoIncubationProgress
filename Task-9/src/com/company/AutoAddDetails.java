package com.company;

import com.company.companiescustomer.dataBase.CustomerDatabase;
import com.company.subscriptionmanagement.controllers.CompanyAuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;

//TODO : update user payment info, notify customer care downgrade plan, track issue

public class AutoAddDetails {

    {
        CompanyAuthenticationController controller = new CompanyAuthenticationController(CompanyAuthenticationController.LoginType.COMPANY);
        controller.register("company1", "company1@gmail.com", "123456789");
        controller.register("company2", "company2@gmail.com", "123456789");

        CompanyController company = new CompanyController( (Company) new CompanyAuthenticationController(CompanyAuthenticationController.LoginType.COMPANY).login("company1@gmail.com", "123456789") );
        company.addProduct("prod1",10,500);
        company.addProduct("prod2",0,500);
        company.addProduct("prod3",10,100);
        company.addSubscriptionPlan("prod1","Regular", SubscriptionPlan.SubscriptionType.MONTHLY,10);
        company.addSubscriptionPlan("prod1","Pro", SubscriptionPlan.SubscriptionType.MONTHLY,13);
        company.addSubscriptionPlan("prod1","Premium", SubscriptionPlan.SubscriptionType.MONTHLY,13);

        company = new CompanyController( (Company) new CompanyAuthenticationController(CompanyAuthenticationController.LoginType.COMPANY).login("company1@gmail.com", "123456789"));
        company.addProduct("product1",10,500);
        company.addProduct("product2",0,500);
        company.addProduct("product3",10,100);

        company.addSubscriptionPlan("product1","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,12);
        company.addSubscriptionPlan("product1","Best Deal", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        company.addSubscriptionPlan("product1","Yearly", SubscriptionPlan.SubscriptionType.YEARLY,13);

        new CompanyDatabase().registerSubscriber("u@c.c","u");
        Subscriber subscriber = new CompanyDatabase().getSubscribersByEmail("u@c.c");
        subscriber.setPaymentDetails(new PaymentDetails(123L,12, LocalDate.now()));

        CustomerDatabase database = new CustomerDatabase();
        database.register("u", "u@c.c", "123456789");
        database.register("u", "u1@c.c", "123456789");
        database.register("u", "u2@c.c", "123456789");

//        SubscriberController subscriberController = new SubscriberController("u","u","c");
//        subscriberController.subscribeProduct("prod1","Regular",null);

//        SubscriberDashboard subscriberDashboard = new SubscriberDashboard(subController.login("u","123"));
//        subscriberDashboard.displaySubscription("company1","prod1");

    }

}