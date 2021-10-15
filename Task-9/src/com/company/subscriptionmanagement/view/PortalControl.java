package com.company.subscriptionmanagement.view;


public class PortalControl{

    public void control(CompanyPortal portal){

        System.out.println("Welcome to the portal");

        while(true){
            int option = -1;
            while(option < 0 || option > 2){
                option = GetValues.getIntegerValue( "0.Previous Menu 1.Register 2.Login ", "Select a valid option");
            }
            switch (option){
                case 0 :
                    return;
                case 1 :
                    portal.registerFlow();
                case 2:
                    portal.loginFlow();
                    break;
            }
        }
    }

}