package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentController;
import com.company.subscriptionmanagement.exception.InputException;

import java.util.HashMap;

public class UPIPaymentView implements PaymentViewable{

    private PaymentController paymentController;

    public UPIPaymentView(PaymentController paymentController){
        this.paymentController = paymentController;
    }

    @Override
    public void view(){
        int pin = -1;
        while(true){
            try{
                System.out.println("not implemented");
                //TODO:
                String upid =  GetValues.getEmail("Enter Upi Id (eg: text442@oksbi.com)");
                if(pin  < 0)
                    pin = GetValues.getIntegerValue("Enter pin", "pin cannot be negative");
                HashMap<String, String> paymentDetails = new HashMap<>();
                paymentDetails.put("upi id", upid);
                paymentDetails.put("pin", Integer.toString(pin));
                paymentController.setPaymentDetails(paymentDetails);
                return;
            }catch(InputException e){
                System.out.println(e.getMessage());
                if(e.getExceptionType() == InputException.ExceptionType.NEGATIVE_VALUE){
                    if(e.getField().equals("pin"))
                        pin = -1;
                }
            }

        }
    }

}