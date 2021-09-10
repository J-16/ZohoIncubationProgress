package com.solidprinciple.InterfaceSegregationPrinciple.Model;

import com.solidprinciple.InterfaceSegregationPrinciple.Interface.ItemWithAuthor;

public class Book implements ItemWithAuthor {
    private String name;
    private double price;
    private String author;

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

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
