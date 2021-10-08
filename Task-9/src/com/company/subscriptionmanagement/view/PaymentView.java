package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.exception.InputException;

import java.util.HashMap;

public class PaymentView{

    public HashMap<String, String> view(){
        long cardNo =  -1;
        int cvv = -1;
        String expDate = null;
        while(true){
            try{
                if(cardNo < 0)
                    cardNo = GetValues.getLongValue(0,"Enter Card Number");
                if(cvv < 0)
                    cvv = GetValues.getIntegerValue(0,"Enter cvv");
                if(expDate == null)
                    expDate = GetValues.getDate("Enter expiry date - yyyy-MM-dd");
                HashMap<String, String> paymentDetails = new HashMap<>();
                paymentDetails.put("cardNo", Long.toString(cardNo));
                paymentDetails.put("cvv", Integer.toString(cvv));
                paymentDetails.put("expDate", expDate);
                return paymentDetails;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("cardNo"))
                        cardNo = -1;
                    if(e.getField().equals("cvv"))
                        cvv = -1;
                    if(e.getField().equals("expDate"))
                        expDate = null;
                }
            }
        }
    }

    public int getPaymentMethod() {
        return GetValues.getIntegerValue(0,"1.Use previous payment details or any other key to enter New Payment details");
    }

}
