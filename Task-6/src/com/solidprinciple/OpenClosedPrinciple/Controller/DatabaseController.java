package com.solidprinciple.OpenClosedPrinciple.Controller;

import com.solidprinciple.OpenClosedPrinciple.Databse.Database;
import com.solidprinciple.OpenClosedPrinciple.Invoice.Invoice;

public class DatabaseController{

    private final Invoice invoice;

    public DatabaseController(Invoice invoice){
        this.invoice = invoice;
    }

    public void save(){
        Database.invoiceDb.add(invoice);
    }

}