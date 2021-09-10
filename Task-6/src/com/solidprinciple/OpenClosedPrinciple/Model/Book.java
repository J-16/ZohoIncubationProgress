package com.solidprinciple.OpenClosedPrinciple.Model;

public class Book {
    private final String name;
    private final String author;
    private double price;

    public Book(String name, String author, double price){
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

}