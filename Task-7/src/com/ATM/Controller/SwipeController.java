package com.ATM.Controller;

import com.ATM.Controller.Helper.ConcreteValidityClass.ATMPinValidity;
import com.ATM.Controller.Helper.ConcreteValidityClass.UserValidity;
import com.ATM.Controller.Helper.Interface.IPinValidity;
import com.ATM.Enum.WithDrawMode;
import com.ATM.Exceptions.BalanceExceptions;
import com.ATM.Model.ConcreteClass.User;

import java.util.Scanner;

public class SwipeController {

    private final IPinValidity atmPinValidity = new ATMPinValidity();
    private UserValidity userValidity  = new UserValidity();

    private final Scanner sc = new Scanner(System.in);
    private User user;

    public void withdraw(){
        user = userValidity.isValidUser();

        if(!atmPinValidity.isValidPin(user)){
            System.out.println("Invalid pin");
            return;
        }

        System.out.println("Enter amount to swipe");
        double amount = sc.nextDouble();
        try {
            user.withdraw(amount, WithDrawMode.SWIPE);
        }catch(BalanceExceptions e){
            System.out.println(e.getMessage());
        }
    }

}