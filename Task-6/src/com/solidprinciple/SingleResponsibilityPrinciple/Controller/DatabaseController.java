package com.solidprinciple.SingleResponsibilityPrinciple.Controller;

import com.solidprinciple.SingleResponsibilityPrinciple.Databse.Database;
import com.solidprinciple.SingleResponsibilityPrinciple.Invoice.Invoice;

public class DatabaseController {

    private final Invoice invoice;

    public DatabaseController(Invoice invoice){
        this.invoice = invoice;
    }

    public void saveInvoice(){
        Database.invoiceDb.add(invoice);
    }

}