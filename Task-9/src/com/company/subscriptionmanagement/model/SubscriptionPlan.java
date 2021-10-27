package com.company.subscriptionmanagement.model;

import java.io.Serializable;

public class SubscriptionPlan implements Serializable {

    public enum SubscriptionType{
        MONTHLY(30), QUARTERLY(180), YEARLY(365);
        int value;
        SubscriptionType(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    private String planName;
    private SubscriptionType subscriptionType;
    private double price;
    private double discount;
    private final long ID;
    private final long companyID;
    private final long productID;

    private static long generateID = 0;

    public SubscriptionPlan(String planName, SubscriptionType subscriptionType, double discount, double price, long companyID, long productID){
        this.planName = planName;
        this.subscriptionType = subscriptionType;
        this.discount = discount;
        this.price = price;
        ID = generateID++;
        this.companyID = companyID;
        this.productID = productID;
        setPrice(discount);
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getPlanName(){
        return planName;
    }

    public void setPlanName(String planName){
        this.planName = planName;
    }

    public double getPrice(){
        return price;
    }

    private void setPrice(double discount){
        if(discount>0)
            this.price = this.price -  (this.price * (discount/100));
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
        setPrice(discount);
    }

    public long getID() {
        return ID;
    }

    public long getCompanyID() {
        return companyID;
    }

    public long getProductID() {
        return productID;
    }
}