package com.solidprinciple.SingleResponsibilityPrinciple.Invoice;

import static com.solidprinciple.SingleResponsibilityPrinciple.Invoice.Invoice.TAX;

public class InvoiceGenerator{

    private final Invoice invoice;

    public InvoiceGenerator(Invoice invoice) {
        this.invoice = invoice;
    }

    public void printInvoice(){
        System.out.println("Book name : " + invoice.getBook().getName());
        System.out.println("Author name : " + invoice.getBook().getAuthor());
        System.out.println("Price : " + invoice.getBook().getPrice());
        System.out.println("Tax : " + TAX);
        System.out.println("Total : " + invoice.getTotal());
    }

}