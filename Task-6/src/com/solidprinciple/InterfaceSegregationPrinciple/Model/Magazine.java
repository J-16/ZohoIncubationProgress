package com.solidprinciple.InterfaceSegregationPrinciple.Model;

import com.solidprinciple.InterfaceSegregationPrinciple.Interface.ItemWithPublisher;

public class Magazine implements ItemWithPublisher {

    private String name;
    private double price;
    private String publisher;

    @Override
    public String getName() {
        return name;
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

    @Override
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

}
