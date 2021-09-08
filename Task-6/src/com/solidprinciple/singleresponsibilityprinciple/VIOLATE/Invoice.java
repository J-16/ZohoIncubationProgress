package com.solidprinciple.singleresponsibilityprinciple.VIOLATE;

import com.solidprinciple.singleresponsibilityprinciple.VIOLATE.Database.Database;
import com.solidprinciple.singleresponsibilityprinciple.VIOLATE.Model.Book;

public class Invoice {
    private final static double TAX = 12;

    private final Book book;
    private final int quantity;
    private double total;

    Invoice(Book book, int quantity){
        this.book = book;
        this.quantity = quantity;
        this.total = calculateTotal();
    }

    public double calculateTotal(){
        return (book.getPrice() * this.quantity) * TAX;
    }

    public void printInvoice(){
        System.out.println("Book name : " + book.getName());
        System.out.println("Author name : " + book.getAuthor());
        System.out.println("Price : " + book.getPrice());
        System.out.println("Tax : " + TAX);
        System.out.println("Total : " + this.total);
    }

    public void saveTOdb(){
        Database.invoiceDb.add(this);
    }

}
