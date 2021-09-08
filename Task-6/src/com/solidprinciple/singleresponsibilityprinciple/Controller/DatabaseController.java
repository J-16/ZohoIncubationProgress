package com.solidprinciple.singleresponsibilityprinciple.Controller;

import com.solidprinciple.singleresponsibilityprinciple.Databse.Database;
import com.solidprinciple.singleresponsibilityprinciple.Invoice.Invoice;

public class DatabaseController extends Database{

    private final Invoice invoice;

    public DatabaseController(Invoice invoice){
        this.invoice = invoice;
    }

    public void saveInvoice(){
        invoiceDb.add(invoice);
    }

}