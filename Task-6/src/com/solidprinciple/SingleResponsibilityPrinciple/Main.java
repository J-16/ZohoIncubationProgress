package com.solidprinciple.SingleResponsibilityPrinciple;

import com.solidprinciple.SingleResponsibilityPrinciple.Controller.InvoiceController;
import com.solidprinciple.SingleResponsibilityPrinciple.Databse.Database;
import com.solidprinciple.SingleResponsibilityPrinciple.Model.Book;

public class Main {

    public static void main(String[] args){
        {
            Database.bookDb.put("Deep work", new Book("Deep work","Cal Newport",200));
            Database.bookDb.put("Atomic habits", new Book("Atomic habits","James Clear",250));
        }

        new InvoiceController().buyBook("Deep work", 2);
        System.out.println();
        try{
            new InvoiceController().buyBook("Deep work", 0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
