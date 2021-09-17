package com.ATM.Controller;

import com.ATM.Controller.Helper.ConcreteValidityClass.ATMPinValidity;
import com.ATM.Controller.Helper.ConcreteValidityClass.UserValidity;
import com.ATM.Controller.Helper.Interface.IPinValidity;
import com.ATM.Exceptions.AmountException;
import com.ATM.Exceptions.BalanceExceptions;
import com.ATM.Model.ConcreteClass.User;
import java.util.Scanner;

public class ATMController {

    private final IPinValidity atmCheck;
    private final UserValidity userValidity = new UserValidity();
    private User user;

    private final Scanner sc = new Scanner(System.in);

    public ATMController(ATMPinValidity atmCheck){
        this.atmCheck = atmCheck;
    }

    public void ATM(){
        user = userValidity.isValidUser();
        if( user == null )
            return;
        if( !atmCheck.isValidPin(user) )
            return;
        do{
            System.out.println("1.Balance 2.Withdraw 3.Deposit 0.Quit");
            int option = sc.nextInt();

            switch(option){
                case 0:
                    return;
                case 1:
                    showBalance();
                    break;
                case 2:
                    withDraw();
                    break;
                case 3:
                    deposit();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

    private void deposit(){
        System.out.println("Enter amount to deposit");
        double amount = sc.nextDouble();
        try{
            user.deposit(amount);
            showBalance();
        }
        catch(AmountException | BalanceExceptions e){
            System.out.println(e.getMessage());
        }
    }

    private void withDraw(){
        System.out.println("Enter amount to withdraw");
        double amount = sc.nextDouble();
        try{
            user.withdraw(amount);
            showBalance();
        }
        catch(AmountException | BalanceExceptions e){
            System.out.println(e.getMessage());
        }
    }

    private void showBalance(){
        System.out.println( "Balance : " + (int) user.getBalance() );
    }

}