package com.solidprinciple.OpenClosedPrinciple.Invoice;

import com.solidprinciple.OpenClosedPrinciple.Controller.DatabaseController;
import com.solidprinciple.OpenClosedPrinciple.Controller.PdfController;
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
        DatabaseController databaseController = new DatabaseController(this);
        databaseController.save();
        PdfController pdfController = new PdfController(this);
        pdfController.save();
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