package com.ATM.View;

import com.ATM.Controller.SwipeController;
import com.ATM.Controller.DatabaseController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        {
            DatabaseController databaseController = new DatabaseController();
            databaseController.registerUser(987456,1234);
            databaseController.registerUser(987453,1234);
        }

        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM();
        NewATM Natm = new NewATM();
        SwipeController swipeController = new SwipeController();

        int c;
        do{
            System.out.println("1.Old ATM 2.New ATM 3.Swipe 0.Quit");
            c = sc.nextInt();
            switch(c){
                case 0:
                    return;
                case 1:
                    atm.call();
                    break;
                case 2:
                    Natm.call();
                    break;
                case 3:
                    swipeController.withdraw();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }

}