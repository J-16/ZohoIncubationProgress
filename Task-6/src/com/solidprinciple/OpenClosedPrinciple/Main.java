package com.solidprinciple.OpenClosedPrinciple;

import com.solidprinciple.OpenClosedPrinciple.Controller.InvoiceController;
import com.solidprinciple.OpenClosedPrinciple.Databse.Database;
import com.solidprinciple.OpenClosedPrinciple.Model.Book;

public class Main {

    public static void main(String[] args){
        {
            Database.bookDb.put("Deep work", new Book("Deep work","Cal Newport",200));
            Database.bookDb.put("Atomic habits", new Book("Atomic habits","James Clear",250));
        }

        new InvoiceController().buyBook("Deep work", 2);
        System.out.println();

    }

}