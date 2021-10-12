package com.company.subscriptionmanagement.view;

public class UPIPaymentView implements PaymentViewable{

    @Override
    public void view(){
        long upid =  GetValues.getLongValue(0,"Enter Upi Id");
        long pin =   GetValues.getIntegerValue(0,"Enter pin");
    }

    @Override
    public void getPaymentMethod(){

    }

}
