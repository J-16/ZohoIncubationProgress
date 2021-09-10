package com.solidprinciple.DependencyInversionPrinciple.Invoice;

import com.solidprinciple.DependencyInversionPrinciple.Model.Book;

public class Invoice{
    public final static double TAX = 12;

    private final Book book;
    private final int quantity;
    private final double total;

    public Invoice(Book book, int quantity){
        this.book = book;
        this.quantity = quantity;
        this.total = calculateTotal();
        new InvoiceGenerator(this).printInvoice();
    }

    public Book getBook() {
        return book;
    }

    public double getTotal() {
        return total;
    }

    public final double calculateTotal(){
        return (book.getPrice() * this.quantity) + ( this.quantity * TAX);
    }

}