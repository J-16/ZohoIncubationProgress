package com.ATM.Controller.Helper;

import com.ATM.Model.Account;

public class BiometricValidity extends PinValidity implements IATMBiometric{

    public boolean isValidBiometric(Account account){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}