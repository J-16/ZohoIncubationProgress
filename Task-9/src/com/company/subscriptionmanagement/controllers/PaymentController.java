package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.service.*;
import com.company.subscriptionmanagement.view.PaymentViewable;

import java.time.LocalDate;
import java.util.HashMap;

public class PaymentController{

    private int option = 1;
    private NotificationService notificationService;
    private PaymentService paymentService;
    private InvoiceService invoiceService;
    private HashMap<String, String> paymentDetails = new HashMap<>();
    private PaymentViewable paymentView;

    public void processPayment(double price, Subscriber subscriber){
        if(subscriber.getPaymentDetails() == null){
            generatePaymentDetails(subscriber);
        }
        else{
            paymentView.getPaymentMethod();
            if( option != 1 ){
                generatePaymentDetails(subscriber);
            }
        }
        makePayment(price, subscriber);
    }

    private void makePayment(double price, Subscriber subscriber){
        paymentService = new CardPaymentService();
        paymentService.makePayment();
        invoiceService = new InvoiceService();
        String invoice = invoiceService.generateInvoice(price, subscriber);
        notificationService = new PushNotificationService();
        notificationService.send(invoice, subscriber);
    }

    private void generatePaymentDetails(Subscriber subscriber){
        paymentView.view();
        if(paymentView instanceof CardPaymentService)
            subscriber.setPaymentDetails( new PaymentDetails( Long.parseLong(paymentDetails.get("cardNo")), Integer.parseInt(paymentDetails.get("cvv")),
                    LocalDate.parse(paymentDetails.get("expDate")) ));
    }

    public void setPaymentDetails(HashMap<String, String> paymentDetails){
        this.paymentDetails = paymentDetails;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public void setPaymentView(PaymentViewable paymentView){
        this.paymentView = paymentView;
    }

}
