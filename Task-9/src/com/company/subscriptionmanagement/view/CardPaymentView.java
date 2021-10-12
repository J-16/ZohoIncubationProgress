package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentController;
import com.company.subscriptionmanagement.exception.InputException;

import java.util.HashMap;

public class CardPaymentView implements PaymentViewable {

    private PaymentController paymentController;

    public CardPaymentView(PaymentController paymentController){
        this.paymentController = paymentController;
    }

    public void view(){
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
                paymentController.setPaymentDetails(paymentDetails);
                return;
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

    public void getPaymentMethod(){
        int option = GetValues.getIntegerValue(0,"1.Use previous payment details or any other key to enter New Payment details");
        paymentController.setOption(option);
    }

}
