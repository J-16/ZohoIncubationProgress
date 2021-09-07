package com;

import com.ATMCenter.NewATM;
import com.ATMCenter.OldATM;
import com.Controller.Helper.ATMCheck;
import com.Controller.SwipeController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c;
        do{
            System.out.println("1.Old ATM 2.New ATM 2.Swipe 0.Quit");
            c = sc.nextInt();

            switch(c){
                case 0:
                    return;
                case 1:
                    OldATM oldATM = new OldATM();
                    oldATM.call();
                    break;
                case 2:
                    NewATM newATM = new NewATM();
                    newATM.call();
                    break;
                case 3:
                    ATMCheck atmCheck = new ATMCheck();
                    SwipeController swipeController = new SwipeController(atmCheck);
                    swipeController.swipe();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }
}