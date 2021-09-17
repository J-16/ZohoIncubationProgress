package com.ATM.Controller.Helper.ConcreteValidityClass;

import com.ATM.Controller.Helper.Interface.IPinValidity;
import com.ATM.Model.ConcreteClass.User;

import java.util.Scanner;

public class ATMPinValidity implements IPinValidity {

    private final Scanner sc = new Scanner(System.in);

    public boolean isValidPin(User user){
        System.out.println("Enter pin :");
        long pin = sc.nextLong();
        return user.getPin() == pin;
    }

}