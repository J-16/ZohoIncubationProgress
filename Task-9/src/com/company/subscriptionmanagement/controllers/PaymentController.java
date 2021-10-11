package com.company.subscriptionmanagement.controllers;

import com.company.subscriptionmanagement.exception.InputException;
import com.company.subscriptionmanagement.model.PaymentDetails;
import com.company.subscriptionmanagement.model.Subscriber;
import com.company.subscriptionmanagement.model.service.InvoiceService;
import com.company.subscriptionmanagement.model.service.NotificationService;
import com.company.subscriptionmanagement.model.service.PaymentService;
import com.company.subscriptionmanagement.model.service.PushNotificationService;
import com.company.subscriptionmanagement.view.PaymentView;
import com.company.subscriptionmanagement.view.PaymentViewable;

import java.time.LocalDate;
import java.util.HashMap;

public class PaymentController implements PaymentControllable{

    private PaymentViewable paymentView = new PaymentView();
    private NotificationService notificationService;
    int option = 1;

    HashMap<String, String> paymentDetails = new HashMap<>();

    public void processPayment(double price, Subscriber subscriber){
        if(subscriber.getPaymentDetails() == null){
            generatePaymentDetails(subscriber);
        }
        else{
            paymentView.getPaymentMethod(this);
            if( option != 1 ){
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
        paymentView.view(this);
        isValidDetails();
        subscriber.setPaymentDetails( new PaymentDetails( Long.parseLong(paymentDetails.get("cardNo")), Integer.parseInt(paymentDetails.get("cvv")),
                LocalDate.parse(paymentDetails.get("expDate")) ));
    }

    private void isValidDetails() {
        if(!paymentDetails.containsKey("cardNo"))
            throw new InputException("Empty fields not allowed", InputException.ExceptionType.NEGATIVE_VALUE, "cardNo");
        if(!paymentDetails.containsKey("cvv"))
            throw new InputException("Empty fields not allowed", InputException.ExceptionType.NEGATIVE_VALUE, "cvv");
        if(!paymentDetails.containsKey("expDate"))
            throw new InputException("Empty fields not allowed", InputException.ExceptionType.NEGATIVE_VALUE, "expDate");
    }

    public void setPaymentDetails(HashMap<String, String> paymentDetails){
        this.paymentDetails = paymentDetails;
    }

    public void setOption(int option) {
        this.option = option;
    }

}
