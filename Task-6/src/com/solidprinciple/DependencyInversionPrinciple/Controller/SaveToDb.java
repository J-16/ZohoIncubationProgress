package com.solidprinciple.DependencyInversionPrinciple.Controller;

import com.solidprinciple.DependencyInversionPrinciple.Databse.Database;
import com.solidprinciple.DependencyInversionPrinciple.Interface.SaveInterface;
import com.solidprinciple.DependencyInversionPrinciple.Invoice.Invoice;

public class SaveToDb implements SaveInterface {

    private final Invoice invoice;

    public SaveToDb(Invoice invoice){
        this.invoice = invoice;
    }

    public void save(){
        Database.invoiceDb.add(invoice);
    }

}