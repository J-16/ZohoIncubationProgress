package com.Controller.Helper;

import com.DataBase.BankDatabase;
import com.Model.Bank;
import com.Model.User;

import java.util.Scanner;

public class ATMCheck extends BankDatabase {

    private final Scanner sc = new Scanner(System.in);
    public Bank user = null;

    public Bank isValidUser(){
        System.out.println("Enter ATM Card Number");
        long cardNo = sc.nextLong();
        try{
            user  = getUser(cardNo);
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return user;
        }
    }

    public boolean isValidPin(){
        System.out.println("Enter pin :");
        int pin = sc.nextInt();
        return user.getPin() == pin;
    }

}
