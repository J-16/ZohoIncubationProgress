package com.company.SubscriptionManagement.Controllers;

import com.company.SubscriptionManagement.Exception.InputException;
import com.company.SubscriptionManagement.Model.ISubscriber;
import com.company.SubscriptionManagement.Model.PaymentDetails;
import com.company.SubscriptionManagement.Model.Service.InvoiceService;
import com.company.SubscriptionManagement.Model.Service.NotificationService;
import com.company.SubscriptionManagement.Model.Service.PaymentService;
import com.company.SubscriptionManagement.View.PaymentView;

import java.util.HashMap;

public class PaymentController {

    public void processPayment(double price, ISubscriber subscriber){

        if(subscriber.getPaymentDetails() == null){
            PaymentView paymentView = new PaymentView();
            HashMap<String, String> paymentDetails = paymentView.view();

            isValidDetails(paymentDetails);

            subscriber.setPaymentDetails( new PaymentDetails( Long.parseLong(paymentDetails.get("cardNo")), Integer.parseInt(paymentDetails.get("cvv")), paymentDetails.get("expDate") ));
        }

        PaymentService paymentService = new PaymentService();
        paymentService.makePayment();
        String invoice = new InvoiceService().generateInvoice(price, subscriber);
        new NotificationService().sendNotification(invoice, subscriber);
    }

    private void isValidDetails(HashMap<String, String> paymentDetails) {
        if( !paymentDetails.containsKey("cardNo") || !paymentDetails.containsKey("cvv") || !paymentDetails.containsKey("expDate") )
            throw new InputException("Missing information");
    }

}
