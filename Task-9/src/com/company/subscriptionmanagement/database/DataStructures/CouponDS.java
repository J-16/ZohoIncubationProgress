package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.CouponDB;
import com.company.subscriptionmanagement.model.Coupon;

import java.util.ArrayList;

public class CouponDS implements CouponDB{

    private ArrayList<Coupon> coupons;

    public CouponDS(){
        coupons = new ArrayList<>();
    }

    @Override
    public void save(Coupon coupon){
        coupons.add(coupon);
    }

    @Override
    public void update() {

    }

    @Override
    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    @Override
    public Coupon getByID(long ID) {
        for(Coupon coupon : coupons){
            if(coupon.getID() == ID)
                return coupon;
        }
        return null;
    }

}
