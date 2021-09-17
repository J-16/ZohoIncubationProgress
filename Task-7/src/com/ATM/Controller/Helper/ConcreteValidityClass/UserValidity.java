package com.ATM.Controller.Helper.ConcreteValidityClass;

import com.ATM.Controller.DatabaseController;
import com.ATM.Model.ConcreteClass.User;

import java.util.Scanner;

public class UserValidity{

    private final Scanner sc = new Scanner(System.in);
    public User user = null;
    DatabaseController databaseController = new DatabaseController();

    private User getUser( long cardNo ){
        return ( User) databaseController.getUser(cardNo);
    }

    public User isValidUser(){
        System.out.println("Enter ATM Card Number");
        long cardNo = sc.nextLong();
        user  = getUser(cardNo);
        return user;
    }

}