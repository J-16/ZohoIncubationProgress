package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.service.InvoiceService;
import com.company.subscriptionmanagement.model.service.NotificationService;
import com.company.subscriptionmanagement.model.service.PaymentService;
import com.company.subscriptionmanagement.model.service.PushNotificationService;
import com.company.subscriptionmanagement.view.PaymentView;

import java.time.LocalDate;
import java.util.HashMap;

public class PaymentController{

    private PaymentView paymentView = new PaymentView();
    private NotificationService notificationService;

    public void processPayment(double price, Subscriber subscriber){
        if(subscriber.getPaymentDetails() == null){
            generatePaymentDetails(subscriber);
        }
        else{
            if( paymentView.getPaymentMethod() != 1){
                generatePaymentDetails(subscriber);
            }
        }
        makePayment(price, subscriber);
    }

    private void makePayment(double price, Subscriber subscriber){
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment();
        String invoice = new InvoiceService().generateInvoice(price, subscriber);
        notificationService = new PushNotificationService();
        notificationService.send(invoice, subscriber);
    }

    private void generatePaymentDetails(Subscriber subscriber){
        HashMap<String, String> paymentDetails = paymentView.view();
        isValidDetails(paymentDetails);
        subscriber.setPaymentDetails( new PaymentDetails( Long.parseLong(paymentDetails.get("cardNo")), Integer.parseInt(paymentDetails.get("cvv")),
                LocalDate.parse(paymentDetails.get("expDate")) ));
    }

    private void isValidDetails(HashMap<String, String> paymentDetails) {
        if( !paymentDetails.containsKey("cardNo") || !paymentDetails.containsKey("cvv") || !paymentDetails.containsKey("expDate") )
            throw new InputException("Missing information");
    }

}
