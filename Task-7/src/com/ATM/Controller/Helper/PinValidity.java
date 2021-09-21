package com.ATM.Controller.Helper;

import com.ATM.Model.Account;
import java.util.Scanner;

public class PinValidity implements IATMPin{

    public boolean isValidPin(Account account){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter pin :");
        long pin = sc.nextLong();
        return account.getCustomerInfo().getPin() == pin;
    }

}