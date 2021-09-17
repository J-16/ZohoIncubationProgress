package com.solidprinciple.InterfaceSegregationPrinciple.Invoice;

import static com.solidprinciple.InterfaceSegregationPrinciple.Invoice.Invoice.TAX;

public class InvoiceGenerator{

    private final Invoice invoice;

    public InvoiceGenerator(Invoice invoice) {
        this.invoice = invoice;
    }

    public String[] printInvoice(){
        String[] details = new String[4];
        details[0] = invoice.getBook().getName();
        details[1] = invoice.getBook().getPrice() + "";
        details[2] = TAX + "";
        details[3] = invoice.getTotal() + "";
        return details;
    }

}
