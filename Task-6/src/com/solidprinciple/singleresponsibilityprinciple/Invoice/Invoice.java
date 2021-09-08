package com.solidprinciple.singleresponsibilityprinciple.Invoice;


import com.solidprinciple.singleresponsibilityprinciple.Controller.DatabaseController;
import com.solidprinciple.singleresponsibilityprinciple.Model.Book;

public class Invoice{
    public final static double TAX = 12;

    private final Book book;
    private final int quantity;
    private double total;

    public Invoice(Book book, int quantity){
        this.book = book;
        this.quantity = quantity;
        this.total = calculateTotal();
        new InvoiceGenerator(this).printInvoice();
        new DatabaseController(this).saveInvoice();
    }

    public static double getTAX() {
        return TAX;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public final double calculateTotal(){
        return (book.getPrice() * this.quantity) + ( this.quantity * TAX);
    }

}
