package com.company;

import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;

//TODO : update user payment info, notify customer care downgrade plan, track issue
//TODO : sort products list and subscription plan by name (CompanyDashboard) and option as per [price,trail]
//TODO : add view for send notification and mail about product updates(news letter)

public class AutoAddDetails {

    {
        AuthenticationController controller = new AuthenticationController();
        controller.register("company1", "company1@gmail.com", "123456789", CompanyDatabase.UserType.COMPANY);
        controller.register("company2", "company2@gmail.com", "123456789",CompanyDatabase.UserType.COMPANY);
        controller.register("company3", "c@c.c", "123456789",CompanyDatabase.UserType.COMPANY);

        CompanyController company = new CompanyController( (Company) new AuthenticationController().login("company1@gmail.com", "123456789",CompanyDatabase.UserType.COMPANY) );
        company.addProduct("prod1",10,500);
        company.addProduct("prod2",0,500);
        company.addProduct("prod3",10,100);
        company.addSubscriptionPlan("prod1","Regular", SubscriptionPlan.SubscriptionType.MONTHLY,10);
        company.addSubscriptionPlan("prod1","Pro", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        company.addSubscriptionPlan("prod1","Premium", SubscriptionPlan.SubscriptionType.YEARLY,13);

        company.addCoupon("prod1","one", LocalDate.parse("2021-12-12"),20);

        company = new CompanyController( (Company) new AuthenticationController().login("company1@gmail.com", "123456789",CompanyDatabase.UserType.COMPANY));
        company.addProduct("product1",10,500);
        company.addProduct("product2",0,500);
        company.addProduct("product3",10,100);

        company.addSubscriptionPlan("product1","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,12);
        company.addSubscriptionPlan("product1","Best Deal", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        company.addSubscriptionPlan("product1","Yearly", SubscriptionPlan.SubscriptionType.YEARLY,13);

        new CompanyDatabase().registerSubscriber("u@c.c","u");
        Subscriber subscriber = new CompanyDatabase().getSubscribersByEmail("u@c.c");
        subscriber.setPaymentDetails(new PaymentDetails(123L,12, LocalDate.now()));

        CompanyDatabase database = new CompanyDatabase();
        database.register("u", "u@c.c", "123456789", CompanyDatabase.UserType.CUSTOMER);
        database.register("u", "u1@c.c", "123456789", CompanyDatabase.UserType.CUSTOMER);
        database.register("u", "u2@c.c", "123456789", CompanyDatabase.UserType.CUSTOMER);

//        SubscriberController subscriberController = new SubscriberController("u","u","c");
//        subscriberController.subscribeProduct("prod1","Regular",null);

//        SubscriberDashboard subscriberDashboard = new SubscriberDashboard(subController.login("u","123"));
//        subscriberDashboard.displaySubscription("company1","prod1");

    }

}