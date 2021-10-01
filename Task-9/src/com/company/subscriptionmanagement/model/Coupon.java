package com.company.subscriptionmanagement.model;

import java.time.LocalDate;

public class Coupon{

    private String couponName;
    private LocalDate expiryDate;
    private double discount;

    public Coupon(String couponName, LocalDate expiryDate, double discount){
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

    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

}
