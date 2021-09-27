package com.company.Model;

public class SubscriberAccount implements ISubscriberAccount{
    private Account account;
    private long cardNumber;
    private int cvv;
    private String expiryDate;
    private final long userId;

    private static long ID_GENERATE = 0;

    public SubscriberAccount(String name, String email, String password){
        this.account = new Account(name,email, password);
        userId = ID_GENERATE++;
    }

    public Account getAccount() {
        return account;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getUserId() {
        return userId;
    }
}