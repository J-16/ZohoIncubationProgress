package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentControllable;


public interface PaymentViewable {

    void view(PaymentControllable paymentController);
    void getPaymentMethod(PaymentControllable paymentController);

}
