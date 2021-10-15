package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentController;

public interface PaymentViewable {

    void view();
    default void getPaymentMethod(PaymentController paymentController){
        int option = GetValues.getIntegerValue("1.Use previous payment details or any other key to enter New Payment details", "Select a valid option");
        paymentController.setOption(option);
    }

}