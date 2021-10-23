package com.company.subscriptionmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Product implements Serializable {

    private String productName;
    private int trailDays;
    private double price;
    private boolean isTrailAvailable = false;
    private final long ID;
    private final long companyID;

    private static long generateID = 0;

    public Product(String productName, int trailDays, double price, long companyID){
        this.productName = productName;
        setTrailDays(trailDays);
        this.price = price;
        this.ID = generateID++;
        this.companyID = companyID;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public int getTrailDays(){
        return trailDays;
    }

    public void setTrailDays(int trailDays){
        if(trailDays > 0){
            isTrailAvailable = true;
            this.trailDays = trailDays;
        }
        this.trailDays = 0;
    }

    public boolean isTrailAvailable() {
        return isTrailAvailable;
    }

    public void setTrailAvailable(boolean trailAvailable) {
        isTrailAvailable = trailAvailable;
    }

    public long getID() {
        return ID;
    }

    public long getCompanyID() {
        return companyID;
    }
}