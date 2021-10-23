package com.company;

import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.controllers.ProductController;
import com.company.subscriptionmanagement.controllers.SubscriberController;
import com.company.subscriptionmanagement.database.CurrentDatabase;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.SubscriptionPlan;

import java.time.LocalDate;

//TODO : update user payment info, notify customer care downgrade plan, track issue
//TODO : sort products list and subscription plan by name (CompanyDashboard) and option as per [price,trail]
//TODO : add view for send notification and mail about product updates(news letter)

public class AutoAddDetails {

    {
        AuthenticationController controller = new AuthenticationController();
        controller.register("company1", "company1@gmail.com", "123456789", UserDB.UserType.COMPANY);
        controller.register("company2", "company2@gmail.com", "123456789",UserDB.UserType.COMPANY);
        controller.register("company3", "c@c.c", "123456789",UserDB.UserType.COMPANY);

        ProductController productController = new ProductController( (Company) new AuthenticationController().login("company1@gmail.com", "123456789", UserDB.UserType.COMPANY) );
        productController.addProduct("prod1",10,500);
        productController.addProduct("prod2",0,500);
        productController.addProduct("prod3",10,100);
        productController.addSubscriptionPlan("prod1","Regular", SubscriptionPlan.SubscriptionType.MONTHLY,10);
        productController.addSubscriptionPlan("prod1","Pro", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        productController.addSubscriptionPlan("prod1","Premium", SubscriptionPlan.SubscriptionType.YEARLY,13);

        productController.addCoupon("prod1","one", LocalDate.parse("2021-12-12"),20);

        productController = new ProductController( (Company) new AuthenticationController().login("company1@gmail.com", "123456789", UserDB.UserType.COMPANY));
        productController.addProduct("product1",10,500);
        productController.addProduct("product2",0,500);
        productController.addProduct("product3",10,100);

        productController.addSubscriptionPlan("product1","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,12);
        productController.addSubscriptionPlan("product1","Best Deal", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        productController.addSubscriptionPlan("product1","Yearly", SubscriptionPlan.SubscriptionType.YEARLY,13);

        UserDB database = CurrentDatabase.getUserDatabase();
        database.register("u", "u@c.c", "123456789", UserDB.UserType.CUSTOMER);
        database.register("u", "u1@c.c", "123456789", UserDB.UserType.CUSTOMER);
        database.register("u", "u2@c.c", "123456789", UserDB.UserType.CUSTOMER);

        CurrentDatabase.getUserDatabase().registerSubscriber("u@c.c","u");
        Subscriber subscriber = CurrentDatabase.getUserDatabase().getSubscribersByEmail("u@c.c");
        CurrentDatabase.getPaymentDetailsDB().save(new PaymentDetails(123L,12, LocalDate.now(), subscriber.getID()));

        String[] letters = {"prod1", "prod2"};
        new SubscriberController("u@c.c","u@c.c","company1").subscribeNewsletter(letters);

//        productController.sendNotificationToSubscribers("prod1","one");
//        productController.sendNotificationToSubscribers("prod1","test");

//        SubscriberController subscriberController = new SubscriberController("u","u","c");
//        subscriberController.subscribeProduct("prod1","Regular",null);

//        SubscriberDashboard subscriberDashboard = new SubscriberDashboard(subController.login("u","123"));
//        subscriberDashboard.displaySubscription("company1","prod1");

    }

}