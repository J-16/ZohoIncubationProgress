package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.view.CardPaymentView;
import com.company.subscriptionmanagement.view.PaymentMethod;
import com.company.subscriptionmanagement.view.PaymentViewable;
import com.company.subscriptionmanagement.view.UPIPaymentView;

public class PaymentMethodController{

    private int option = -1;

    public PaymentViewable getPaymentMethod(PaymentController paymentController){
        PaymentMethod paymentMethod = new PaymentMethod(this);
        paymentMethod.selectPayment();
        switch (option){
            case 1:
                return new UPIPaymentView();
            case 2:
                //Internet banking view
            case 3:
                return new CardPaymentView(paymentController);
        }
        return null;
    }

    public void setPaymentMethod(int option){
        this.option = option;
    }
}
