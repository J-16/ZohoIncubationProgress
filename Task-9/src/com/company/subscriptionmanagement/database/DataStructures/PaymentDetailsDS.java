package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.PaymentDetailsDB;
import com.company.subscriptionmanagement.model.PaymentDetails;

import java.util.ArrayList;

public class PaymentDetailsDS implements PaymentDetailsDB{

    ArrayList<PaymentDetails> paymentDetails;

    public PaymentDetailsDS(){
        paymentDetails = new ArrayList<>();
    }

    @Override
    public void save(PaymentDetails paymentDetail) {
        paymentDetails.add(paymentDetail);
    }

    @Override
    public void update(PaymentDetails updatedPaymentDetail){
        for(PaymentDetails paymentDetail : paymentDetails){
            if(paymentDetail.getID() == updatedPaymentDetail.getID()){
                paymentDetails.remove(paymentDetail);
                paymentDetails.add(updatedPaymentDetail);
                return;
            }
        }
    }

    @Override
    public PaymentDetails getBySubscriberID(long subscriberID) {
        for(PaymentDetails paymentDetail : paymentDetails){
            if(paymentDetail.getSubscriberID() == subscriberID){
                return paymentDetail;
            }
        }
        return null;
    }
}
