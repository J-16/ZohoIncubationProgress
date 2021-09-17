package com.ATM.Controller.Helper.ConcreteValidityClass;

import com.ATM.Model.ConcreteClass.User;

import java.util.Scanner;

public class BiometricValidity extends ATMPinValidity {

    @Override
    public boolean isValidPin(User user){

        System.out.println("1.Pin 2.FingerPrint");
        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();

        if(option == 1)
            return super.isValidPin(user);
        else{
            System.out.println("Please place your finger");
            return biometric();
        }

    }

    public boolean biometric(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}