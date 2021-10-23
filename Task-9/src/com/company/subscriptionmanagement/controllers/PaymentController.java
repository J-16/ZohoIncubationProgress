package com.company.subscriptionmanagement.controllers;


import com.company.subscriptionmanagement.database.CurrentDatabase;
import com.company.subscriptionmanagement.database.PaymentDetailsDB;
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
    private double price;
    private Subscriber subscriber;
    private PaymentDetailsDB paymentDetailsDB;

    public PaymentController(double price, Subscriber subscriber){
        this.price = price;
        this.subscriber = subscriber;
        this.paymentDetailsDB = CurrentDatabase.getPaymentDetailsDB();
    }

    public void processPayment(){
        if(paymentDetailsDB.getBySubscriberID(subscriber.getAccount().getID()) == null){
            generatePaymentDetails();
        }
        else{
            paymentView.getPaymentMethod(this);
            if( option != 1 ){
                generatePaymentDetails();
            }
        }
        makePayment();
    }

    private void makePayment(){
        paymentService.makePayment();
        invoiceService = new InvoiceService();
        String invoice = invoiceService.generateInvoice(price, subscriber);
        notificationService = new PushNotificationService();
        notificationService.send(invoice, subscriber);
    }

    private void generatePaymentDetails(){
        paymentView.view();
        if(paymentView instanceof CardPaymentService)
            paymentDetailsDB.save( new PaymentDetails( Long.parseLong(paymentDetails.get("cardNo")), Integer.parseInt(paymentDetails.get("cvv")),
                    LocalDate.parse(paymentDetails.get("expDate")), subscriber.getAccount().getID() ));
        //cannot save upi details and internet banking (only one time payment).
    }

    public void setPaymentDetails(HashMap<String, String> paymentDetails){
        this.paymentDetails = paymentDetails;
    }

    public void setOption(int option){
        this.option = option;
    }

    public void setPaymentView(PaymentViewable paymentView){
        this.paymentView = paymentView;
    }

    public void setPaymentService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

}
