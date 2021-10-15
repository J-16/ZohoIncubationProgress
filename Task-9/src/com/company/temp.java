package com.company;

import com.company.subscriptionmanagement.controllers.AuthenticationController;
import com.company.subscriptionmanagement.controllers.CompanyController;
import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.view.CompanyDashboard;
import com.company.subscriptionmanagement.view.DisplayMessage;

public class temp{

    public static void main(String ...args){
        new AutoAddDetails();
        Company company = (Company) new AuthenticationController().login("company1@gmail.com","123456789", CompanyDatabase.UserType.COMPANY);
        CompanyDashboard companyDashboard = new CompanyDashboard(new CompanyController(company));
        //companyDashboard.displaySubscriptions("prod1");

        //DisplayMessage.list("list");
    }

}