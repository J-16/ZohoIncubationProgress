package com;

import com.Controller.Helper.ATMCheck;
import com.Controller.Helper.NewATMCheck;
import com.Controller.SwipeController;
import com.Controller.ATMController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMCheck atmCheck = new ATMCheck();
        ATMController atmController;

        int c;
        do{
            System.out.println("1.ATM 2.New ATM 2.Swipe 0.Quit");
            c = sc.nextInt();

            switch(c){
                case 0:
                    return;
                case 1:
                    atmController = new ATMController(atmCheck);
                    atmController.ATM();
                    break;
                case 2:
                    NewATMCheck newATMCheck = new NewATMCheck();
                    atmController = new ATMController(newATMCheck);
                    atmController.ATM();
                    break;
                case 3:
                    SwipeController swipeController = new SwipeController(atmCheck);
                    swipeController.swipe();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(true);
    }
}