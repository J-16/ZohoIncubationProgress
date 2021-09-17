package com.solidprinciple.OpenClosedPrinciple.Invoice;

import com.solidprinciple.OpenClosedPrinciple.Controller.SaveToDb;
import com.solidprinciple.OpenClosedPrinciple.Controller.SaveToFile;
import com.solidprinciple.OpenClosedPrinciple.Model.Book;

public class Invoice{
    public final static double TAX = 12;

    private final Book book;
    private final int quantity;
    private final double total;

    public Invoice(Book book, int quantity){
        this.book = book;
        this.quantity = quantity;
        this.total = calculateTotal();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(this);
        invoiceGenerator.printInvoice();
        SaveToDb databaseController = new SaveToDb(this);
        databaseController.save();
        databaseController = new SaveToFile(this);
        databaseController.save();
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