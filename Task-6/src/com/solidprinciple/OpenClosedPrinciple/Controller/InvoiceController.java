package com.solidprinciple.OpenClosedPrinciple.Controller;

import com.solidprinciple.OpenClosedPrinciple.Databse.Database;
import com.solidprinciple.OpenClosedPrinciple.Invoice.Invoice;

public class InvoiceController {

    public void buyBook(String bookName, int quantity){
        if(quantity <=0 ){
            throw new RuntimeException("invalid quantity");
        }
        new Invoice(Database.bookDb.get(bookName), quantity);
    }

}