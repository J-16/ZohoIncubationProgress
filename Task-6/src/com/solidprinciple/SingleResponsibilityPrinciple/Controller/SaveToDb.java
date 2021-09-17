package com.solidprinciple.SingleResponsibilityPrinciple.Controller;

import com.solidprinciple.SingleResponsibilityPrinciple.Databse.Database;
import com.solidprinciple.SingleResponsibilityPrinciple.Invoice.Invoice;

public class SaveToDb {

    private final Invoice invoice;

    public SaveToDb(Invoice invoice){
        this.invoice = invoice;
    }

    public void save(){
        Database.invoiceDb.add(invoice);
    }

}