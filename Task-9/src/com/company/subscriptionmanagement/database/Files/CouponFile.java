package com.company.subscriptionmanagement.database.Files;

import com.company.subscriptionmanagement.database.CouponDB;
import com.company.subscriptionmanagement.model.Coupon;

import java.io.File;
import java.util.ArrayList;

public class CouponFile implements CouponDB{

    private File couponFile = new File("coupon.csv");

    public void save(Coupon issue){

    }

    public void update(){

    }

    @Override
    public ArrayList<Coupon> getCoupons() {
        return null;
    }

    @Override
    public Coupon getByID() {
        return null;
    }

}
