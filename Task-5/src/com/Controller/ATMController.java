package com.Controller;

import com.Controller.Helper.ATMCheck;

import java.util.Scanner;

public class ATMController{

    private final ATMCheck atmCheck;
    private final Scanner sc = new Scanner(System.in);

    public ATMController(ATMCheck atmCheck){
        this.atmCheck = atmCheck;
    }

    public void ATM(){
        atmCheck.user = atmCheck.isValidUser();
        if( atmCheck.user == null )
            return;
        if(!atmCheck.isValidPin())
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
        atmCheck.user.deposit(amount);
    }

    private void withDraw(){
        System.out.println("Enter amount to withdraw");
        double amount = sc.nextDouble();
        atmCheck.user.withdraw(amount);
    }

    private void showBalance(){
        atmCheck.user.displayBalance();
    }

}