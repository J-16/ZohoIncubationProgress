package com.ATM.Controller;

import com.ATM.Controller.Helper.PinValidity;
import com.ATM.Controller.Helper.UserValidity;
import com.ATM.Model.Account;

import java.util.Scanner;

public class ATMController extends TransactionController{

    private final PinValidity atmValidity = new PinValidity();
    private final UserValidity userValidity = new UserValidity();
    private Account account;

    private final Scanner sc = new Scanner(System.in);

    public void ATM(){
        account = userValidity.isValidUser();

        if( !atmValidity.isValidPin(account) )
            return;

        do{
            System.out.println("1.Balance 2.Withdraw 3.Deposit 0.Quit");
            int option = sc.nextInt();

            switch(option){
                case 0:
                    return;
                case 1:
                    showBalance(account);
                    break;
                case 2:
                    withDraw(account);
                    break;
                case 3:
                    deposit(account);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }



}