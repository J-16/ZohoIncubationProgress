package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentMethodController;

public class PaymentMethod{

    private PaymentMethodController paymentMethodController;

    public PaymentMethod(PaymentMethodController paymentMethodController){
        this.paymentMethodController = paymentMethodController;
    }

    public void selectPayment(){
        int option = -1;
        while(option != 3){
            option = GetValues.getIntegerValue(0,"1.UPI 2.Internet Banking 3.Card Payment (Only card method is implemented for now)");
        }
        paymentMethodController.setPaymentMethod(option);
    }

}
