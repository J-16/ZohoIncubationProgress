package com.solidprinciple.InterfaceSegregationPrinciple.Presenter;

import com.solidprinciple.InterfaceSegregationPrinciple.Databse.Database;
import com.solidprinciple.InterfaceSegregationPrinciple.Invoice.Invoice;

public class SaveToDb {

    private final Invoice invoice;

    public SaveToDb(Invoice invoice){
        this.invoice = invoice;
    }

    public void saveInvoice(){
        Database.invoiceDb.add(invoice);
    }

}