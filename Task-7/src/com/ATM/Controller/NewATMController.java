package com.ATM.Controller;

import com.ATM.Controller.Helper.BiometricValidity;
import com.ATM.Controller.Helper.UserValidity;
import com.ATM.Model.Account;

import java.util.Scanner;

public class NewATMController extends TransactionController{

    private final BiometricValidity biometricValidity = new BiometricValidity();
    private final UserValidity userValidity = new UserValidity();
    private Account account;

    private final Scanner sc = new Scanner(System.in);

    private boolean VerificationMethod(){
        System.out.println("1.Pin 2.FingerPrint");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        if(option == 1)
            return biometricValidity.isValidPin(account);

        return biometricValidity.isValidBiometric(account);
    }

    public void ATM(){
        account = userValidity.isValidUser();

        if( !VerificationMethod() )
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
