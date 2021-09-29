package com.company;

import com.company.SubscriptionManagement.Controllers.CompanyAuthenticationController;
import com.company.SubscriptionManagement.Controllers.CompanyController;
import com.company.companiesuser.Controller.UserAuthenticationController;
import com.company.SubscriptionManagement.Model.SubscriptionPlan;

public class AutoAddDetails {

    {
        CompanyAuthenticationController controller = new CompanyAuthenticationController();
        controller.register("c", "c", "123");
        controller.register("c1", "c2@c.com", "123");

        CompanyController company = new CompanyAuthenticationController().login("c", "123");
        company.addProduct("prod1",10,500);
        company.addProduct("prod2",0,500);
        company.addProduct("prod3",10,100);

        company.addSubscriptionPlan("prod1","Regular", SubscriptionPlan.SubscriptionType.MONTHLY,10);
        company.addSubscriptionPlan("prod1","Pro", SubscriptionPlan.SubscriptionType.MONTHLY,13);
        company.addSubscriptionPlan("prod1","Premium", SubscriptionPlan.SubscriptionType.MONTHLY,13);

        company.addSubscriptionPlan("prod2","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,12);
        company.addSubscriptionPlan("prod2","Best Deal", SubscriptionPlan.SubscriptionType.QUARTERLY,13);
        company.addSubscriptionPlan("prod2","Yearly", SubscriptionPlan.SubscriptionType.YEARLY,13);

        UserAuthenticationController subController = new UserAuthenticationController();
        subController.register("sub1","u","123");

//        SubscriberDashboard subscriberDashboard = new SubscriberDashboard(subController.login("u","123"));
//        subscriberDashboard.displaySubscription("company1","prod1");
    }

}