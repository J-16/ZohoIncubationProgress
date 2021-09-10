package com.solidprinciple.InterfaceSegregationPrinciple.Model;

import com.solidprinciple.InterfaceSegregationPrinciple.Interface.Item;

public class NewsPaper implements Item {

    private String name;
    private double price;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    
}
