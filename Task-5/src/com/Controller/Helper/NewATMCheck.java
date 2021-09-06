package com.Controller.Helper;

public class NewATMCheck extends ATMCheck{

    @Override
    public boolean isValidPin(){
        System.out.println("Please place your finger");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
