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
        long upid =  GetValues.getLongValue(0,"Enter Upi Id");
        long pin =   GetValues.getIntegerValue(0,"Enter pin");
    }

    @Override
    public void getPaymentMethod(){

    }

}
