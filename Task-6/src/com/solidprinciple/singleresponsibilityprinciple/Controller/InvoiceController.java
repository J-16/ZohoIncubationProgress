package com.solidprinciple.singleresponsibilityprinciple.Controller;

import com.solidprinciple.singleresponsibilityprinciple.Databse.Database;
import com.solidprinciple.singleresponsibilityprinciple.Invoice.Invoice;

public class InvoiceController {

    public void buyBook(String bookName, int quantity){
        if(quantity <=0 ){
            throw new RuntimeException("invalid quantity");
        }
        new Invoice(Database.bookDb.get(bookName), quantity);
    }

}