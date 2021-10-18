package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentMethodController;

public class PaymentMethod{

    private PaymentMethodController paymentMethodController;

    public PaymentMethod(PaymentMethodController paymentMethodController){
        this.paymentMethodController = paymentMethodController;
    }

    public void selectPayment(){
        int option = -1;
        while(option != 0 && option != 3 && option != 1){
            option = GetValues.getIntegerValue("0.return 1.UPI 2.Internet Banking 3.Card Payment (1 and 3 are implemented for now)", "Select 1 or 3 only");
        }
        paymentMethodController.setPaymentMethod(option);
    }

}
