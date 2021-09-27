package com.ATM.Controller;

import com.ATM.Controller.Helper.PinValidity;
import com.ATM.Controller.Helper.UserValidity;
import com.ATM.Exceptions.BalanceExceptions;
import com.ATM.Model.Account;

import java.util.Scanner;

enum TransactionMode {
    ATM,SWIPE;
}

public class SwipeController {

    private final PinValidity aTMValidity = new PinValidity();
    private UserValidity userValidity  = new UserValidity();

    private final Scanner sc = new Scanner(System.in);
    private Account account;

    public void withdraw(){
        account = userValidity.isValidUser();

        if(!aTMValidity.isValidPin(account)){
            System.out.println("Invalid pin");
            return;
        }

        System.out.println("Enter amount to swipe");
        double amount = sc.nextDouble();
        try {
            account.swipe(amount);
        }catch(BalanceExceptions e){
            System.out.println(e.getMessage());
        }
    }

}