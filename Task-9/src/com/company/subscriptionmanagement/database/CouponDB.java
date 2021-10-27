package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Coupon;

import java.util.ArrayList;

public interface CouponDB{
    public void save(Coupon issue);
    public void update();
    public ArrayList<Coupon> getCoupons();
    public Coupon getByID(long ID);
}
