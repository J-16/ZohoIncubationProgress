package com.Controller.Helper;

import java.util.Scanner;

public class NewATMCheck extends ATMCheck{

    private boolean biometric(){
        System.out.println("Please place your finger");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean isValidPin(){
        System.out.println("1.Pin 2.FingerPrint");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if(option == 1)
            return super.isValidPin();
        else
            return biometric();
    }

}
