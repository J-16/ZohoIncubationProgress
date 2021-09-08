package com;

import com.ATMCenter.NewATM;
import com.ATMCenter.OldATM;
import com.Controller.Helper.ATMCheck;
import com.Controller.SwipeController;
import com.DataBase.BankDatabase;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        {
            BankDatabase bankDatabase = new BankDatabase();
            bankDatabase.registerUser(987456,1234);
            bankDatabase.registerUser(987453,1234);
        }

        Scanner sc = new Scanner(System.in);
        OldATM oldATM = new OldATM();
        NewATM newATM = new NewATM();
        ATMCheck atmCheck = new ATMCheck();
        SwipeController swipeController = new SwipeController(atmCheck);

        int c;
        do{
            System.out.println("1.Old ATM 2.New ATM 2.Swipe 0.Quit");
            c = sc.nextInt();

            switch(c){
                case 0:
                    return;
                case 1:
                    oldATM.call();
                    break;
                case 2:
                    newATM.call();
                    break;
                case 3:
                    swipeController.swipe();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }
}