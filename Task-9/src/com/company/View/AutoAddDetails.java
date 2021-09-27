package com.company.View;

import com.company.Controllers.CompanyAuthenticationController;
import com.company.Controllers.CompanyController;
import com.company.Database.Database;
import com.company.Model.SubscriptionPlan;

public class AutoAddDetails {

    {
        CompanyAuthenticationController controller = new CompanyAuthenticationController();
        controller.register("company1", "c1@c.com", "123");
        controller.register("company2", "c2@c.com", "123");

        CompanyController company = new CompanyController(Database.getCompanyByEmail("c1@c.com"));
        company.addProduct("prod1",10,100);
        company.addProduct("prod2",0,500);
        company.addProduct("prod3",10,100);

        company.addSubscriptionPlan("prod1","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,12);
        company.addSubscriptionPlan("prod1","Monthly", SubscriptionPlan.SubscriptionType.MONTHLY,13);
    }

}