package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentController;

public interface PaymentViewable {

    void view();
    default void getPaymentMethod(PaymentController paymentController){
        int option = GetValues.getIntegerValue("1.Use saved payment details (you have already used our service and we have saved your card details) or any other key to " +
                "enter New Payment details", "Select a valid option");
        paymentController.setOption(option);
    }

}