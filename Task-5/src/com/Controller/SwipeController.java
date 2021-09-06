package com.Controller;

import com.Controller.Helper.ATMCheck;
import com.Enum.WithDrawMode;

import java.util.Scanner;

public class SwipeController {

    private final ATMCheck atmCheck;
    private final Scanner sc = new Scanner(System.in);

    public SwipeController(ATMCheck atmCheck){
        this.atmCheck = atmCheck;
    }

    public void swipe(){
        atmCheck.user = atmCheck.isValidUser();
        if( atmCheck.user == null )
            return;
        if(!atmCheck.isValidPin())
            return;
        System.out.println("Enter amount to swipe");
        double amount = sc.nextDouble();
        atmCheck.user.withdraw(amount, WithDrawMode.SWIPE);
    }

}
