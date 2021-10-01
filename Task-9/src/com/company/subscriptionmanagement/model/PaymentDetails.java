package com.company.subscriptionmanagement.model;

import java.time.LocalDate;

public class PaymentDetails{

    private long cardNumber;
    private int cvv;
    private LocalDate expiryDate;

    public PaymentDetails(long cardNumber, int cvv, LocalDate expiryDate){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public Long getCardNumber() {
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

}
