package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentController;

public class UPIPaymentView implements PaymentViewable{

    private PaymentController paymentController;

    public UPIPaymentView(PaymentController paymentController){
        this.paymentController = paymentController;
    }

    @Override
    public void view(){
        System.out.println("not implemented");
        long upid =  GetValues.getLongValue("Enter Upi Id", "Upi cannot be negative");
        long pin =   GetValues.getIntegerValue("Enter pin", "pin cannot be negative");
    }

    @Override
    public void getPaymentMethod(){

    }

}
