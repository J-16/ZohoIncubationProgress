package com.ATM.Controller.Helper;

import com.ATM.Model.Account;
import java.util.Scanner;

public class PinValidity implements IATMPin{

    public boolean isValidPin(Account account){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter pin :");
        long pin = sc.nextLong();
        boolean valid = account.getCustomerInfo().getPin() == pin;
        if(!valid){
            System.out.println("Invalid Pin");
            return false;
        }
        return true;
    }

}