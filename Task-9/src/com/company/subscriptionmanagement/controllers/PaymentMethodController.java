package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.model.service.CardPaymentService;
import com.company.subscriptionmanagement.model.service.UPIPaymentService;
import com.company.subscriptionmanagement.view.CardPaymentView;
import com.company.subscriptionmanagement.view.PaymentMethod;
import com.company.subscriptionmanagement.view.UPIPaymentView;

public class PaymentMethodController{

    private int option = -1;

    public void getPaymentMethod(PaymentController paymentController, double price){
        System.out.println("Amount to be paid : " + price);
        PaymentMethod paymentMethod = new PaymentMethod(this);
        paymentMethod.selectPayment();
        switch (option){
            case 1:
                paymentController.setPaymentService(new UPIPaymentService());
                paymentController.setPaymentView(new UPIPaymentView(paymentController));
                return;
            case 2:
                //Internet banking view
            case 3:
                paymentController.setPaymentService(new CardPaymentService());
                paymentController.setPaymentView(new CardPaymentView(paymentController));
        }
    }

    public void setPaymentMethod(int option){
        this.option = option;
    }

}