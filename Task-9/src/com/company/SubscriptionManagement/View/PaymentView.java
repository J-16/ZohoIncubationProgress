package com.company.SubscriptionManagement.View;

import java.util.HashMap;
import java.util.Scanner;

public class PaymentView{

    public HashMap<String, String> view(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Card Number");
        long cardNo = sc.nextLong();
        System.out.println("Enter cvv");
        int cvv = sc.nextInt();
        System.out.print("Enter Exp date");
        String expDate = sc.next();
        HashMap<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNo", Long.toString(cardNo));
        paymentDetails.put("cvv", Integer.toString(cvv));
        paymentDetails.put("expDate", expDate);
        return paymentDetails;
    }
}
