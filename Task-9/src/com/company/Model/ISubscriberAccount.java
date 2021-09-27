package com.company.Model;

public interface ISubscriberAccount{
    Account getAccount();
    long getCardNumber();
    void setCardNumber(long cardNumber);
    int getCvv();
    void setCvv(int cvv);
    String getExpiryDate();
    void setExpiryDate(String expiryDate);
    long getUserId();
}
