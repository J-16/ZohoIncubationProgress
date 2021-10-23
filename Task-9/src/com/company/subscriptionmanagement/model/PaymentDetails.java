package com.company.subscriptionmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentDetails implements Serializable {

    private long cardNumber;
    private int cvv;
    private LocalDate expiryDate;
    private final long ID;
    private final long subscriberID;

    private static long generateID = 0;

    public PaymentDetails(long cardNumber, int cvv, LocalDate expiryDate, long subscriberID){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.ID = generateID++;
        this.subscriberID = subscriberID;
    }

    public Long getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getID(){
        return ID;
    }

    public long getSubscriberID(){
        return subscriberID;
    }
}
