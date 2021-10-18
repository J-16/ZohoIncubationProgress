package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.exception.TransactionException;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.service.CardPaymentService;
import com.company.subscriptionmanagement.model.service.UPIPaymentService;
import com.company.subscriptionmanagement.view.CardPaymentView;
import com.company.subscriptionmanagement.view.PaymentMethod;
import com.company.subscriptionmanagement.view.UPIPaymentView;

public class PaymentMethodController{

    private int option = -1;

    private PaymentController paymentController;
    private Subscriber subscriber;
    private double priceTObePaid;
    private double actualPrice;

    public PaymentMethodController(double actualPrice, double priceTObePaid, Subscriber subscriber, PaymentController paymentController) {
        this.actualPrice = actualPrice;
        this.priceTObePaid = priceTObePaid;
        this.paymentController = paymentController;
        this.subscriber = subscriber;
    }

    public void getPaymentMethod(){
        if(priceTObePaid != actualPrice)
            System.out.println("Price : " + actualPrice);
        System.out.println("Amount to be paid : " + priceTObePaid);
        PaymentMethod paymentMethod = new PaymentMethod(this);
        paymentMethod.selectPayment();
        switch (option){
            case 0:
                throw new TransactionException("Transaction cancelled");
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