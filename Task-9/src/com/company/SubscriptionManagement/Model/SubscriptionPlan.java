package com.company.SubscriptionManagement.Model;

public class SubscriptionPlan{

    public enum SubscriptionType{
        MONTHLY, QUARTERLY, YEARLY;
    }

    private String planName;
    private SubscriptionType subscriptionType;
    private double price;
    private double discount;

    public SubscriptionPlan(String planName, SubscriptionType subscriptionType, double discount, double price){
        this.planName = planName;
        this.subscriptionType = subscriptionType;
        this.discount = discount;
        this.price = price;
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
}