package com.solidprinciple.SingleResponsibilityPrinciple.Controller;

import com.solidprinciple.SingleResponsibilityPrinciple.Databse.Database;
import com.solidprinciple.SingleResponsibilityPrinciple.Invoice.Invoice;

public class InvoiceController {

    public void buyBook(String bookName, int quantity){
        if(quantity <=0 ){
            throw new RuntimeException("invalid quantity");
        }
        new Invoice(Database.bookDb.get(bookName), quantity);
    }

}