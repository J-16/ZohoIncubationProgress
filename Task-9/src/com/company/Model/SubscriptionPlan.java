package com.company.Model;


public class SubscriptionPlan{

    public enum SubscriptionType{
        MONTHLY, QUARTERLY, YEARLY;
    }

    private String planName;
    private SubscriptionType subscriptionType;
    private double price;
    private double discount;

    public SubscriptionPlan(String planName, SubscriptionType subscriptionType, double discount){
        this.planName = planName;
        this.subscriptionType = subscriptionType;
        this.discount = discount;
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

    public SubscriptionType getPaymentType(){
        return subscriptionType;
    }

    public void setPaymentType(SubscriptionType paymentType){
        this.subscriptionType = paymentType;
    }

    public double getPrice(){
        return price;
    }

    private void setPrice(double discount){
        this.price = this.price * (discount/100);
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
        setPrice(discount);
    }
}