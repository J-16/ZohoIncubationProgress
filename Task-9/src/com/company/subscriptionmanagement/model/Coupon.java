package com.company.subscriptionmanagement.model;

import java.time.LocalDate;

public class Coupon{

    private String couponName;
    private LocalDate expiryDate;
    private double discount;
    private final long ID;
    private final long productID;
    private final long companyID;
    private static long generateID = 0;

    public Coupon(String couponName, LocalDate expiryDate, double discount, long companyID, long productID){
        this.couponName = couponName;
        this.expiryDate = expiryDate;
        this.discount = discount;
        this.ID = generateID++;
        this.productID = productID;
        this.companyID = companyID;
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

    public long getID(){
        return ID;
    }

    public long getProductID(){
        return productID;
    }
}
