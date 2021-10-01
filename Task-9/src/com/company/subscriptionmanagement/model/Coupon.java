package com.company.subscriptionmanagement.model;

public class Coupon{
    private String couponName;
    private int expiryDate;
    private double discount;

    public Coupon(String couponName, int expiryDate, double discount){
        this.couponName = couponName;
        this.expiryDate = expiryDate;
        this.discount = discount;
    }

    public String getCouponName(){
        return couponName;
    }

    public void setCouponName(String couponName){
        this.couponName = couponName;
    }

    public int getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate){
        this.expiryDate = expiryDate;
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

}
