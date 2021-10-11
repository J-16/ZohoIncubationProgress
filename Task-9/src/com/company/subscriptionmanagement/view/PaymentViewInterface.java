package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.controllers.PaymentController;


public interface PaymentViewInterface{

    void view(PaymentController paymentController);
    void getPaymentMethod(PaymentController paymentController);

}
