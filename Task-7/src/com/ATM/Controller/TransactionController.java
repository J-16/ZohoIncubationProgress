package com.ATM.Controller;

import com.ATM.Exceptions.AmountException;
import com.ATM.Exceptions.BalanceExceptions;
import com.ATM.Model.Account;

import java.util.Scanner;

public class TransactionController {

    private final Scanner sc = new Scanner(System.in);

    protected void deposit(Account account){
        System.out.println("Enter amount to deposit");
        double amount = sc.nextDouble();
        try{
            account.deposit(amount);
            showBalance(account);
        }
        catch(AmountException | BalanceExceptions e){
            System.out.println(e.getMessage());
        }
    }

    protected void withDraw(Account account){
        System.out.println("Enter amount to withdraw");
        double amount = sc.nextDouble();
        try{
            account.withdraw(amount);
            showBalance(account);
        }
        catch(AmountException | BalanceExceptions e){
            System.out.println(e.getMessage());
        }
    }

    protected void showBalance(Account account){
        System.out.println( "Balance : " + (int) account.getBalance() );
    }

}
