package com.solidprinciple.DependencyInversionPrinciple.Controller;

import com.solidprinciple.DependencyInversionPrinciple.Databse.Database;
import com.solidprinciple.DependencyInversionPrinciple.Interface.SaveInterface;
import com.solidprinciple.DependencyInversionPrinciple.Invoice.Invoice;

public class InvoiceController {

    private SaveInterface saveInterface;

    public void buyBook(String bookName, int quantity){

        if(quantity <=0 ){
            throw new RuntimeException("invalid quantity");
        }

        Invoice invoice = new Invoice(Database.bookDb.get(bookName), quantity);

        saveInterface = new SaveToDb( invoice );
        save();
        saveInterface = new SaveToFile( invoice );
        save();
    }

    private void save(){
        saveInterface.save();
    }

}