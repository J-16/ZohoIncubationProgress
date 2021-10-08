package com.company.subscriptionmanagement.view;


import java.util.HashMap;
import java.util.Scanner;

public class PaymentView{

    public HashMap<String, String> view(){
        Scanner sc = new Scanner(System.in);
        long cardNo = GetValues.getLongValue(0,"Enter Card Number");
        int cvv = GetValues.getIntegerValue(0,"Enter cvv");
        String expDate = GetValues.getDate("Enter expiry date - yyyy-MM-dd");
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
