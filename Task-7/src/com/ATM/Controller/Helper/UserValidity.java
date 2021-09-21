package com.ATM.Controller.Helper;

import com.ATM.DataBase.BankDatabase;
import com.ATM.Model.Account;

import java.util.Scanner;

public class UserValidity{

    private Account getUser(long cardNo ){
        return BankDatabase.getCustomer(cardNo);
    }

    public Account isValidUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ATM Card Number");
        long cardNo = sc.nextLong();
        return getUser(cardNo);
    }

}