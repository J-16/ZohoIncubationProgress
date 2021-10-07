package com.company.subscriptionmanagement.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PaymentView{

    public HashMap<String, String> view(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Card Number");
        long cardNo;
        try{
            cardNo = sc.nextLong();
        }catch(InputMismatchException e){
            System.out.println("Invalid card details, enter again");
            cardNo = sc.nextLong();
        }
        System.out.println("Enter cvv");
        int cvv;
        try{
            cvv = sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Invalid card details, enter again");
            cvv = sc.nextInt();
        }
        String expDate = null;
        boolean isDate = false;
        while(!isDate){
            try{
                System.out.print("Enter Exp date yyyy-MM-dd");
                expDate = sc.next();
                LocalDate.parse(expDate);
                isDate = true;
            }catch(DateTimeParseException e){
                System.out.println("Incorrect date format, enter yyyy-MM-dd");
                expDate = sc.next();
            }
        }
        HashMap<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNo", Long.toString(cardNo));
        paymentDetails.put("cvv", Integer.toString(cvv));
        paymentDetails.put("expDate", expDate);
        return paymentDetails;
    }

    public int getPaymentMethod() {
        System.out.println("1.Use previous payment details or any other key to enter New Payment details");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

}
