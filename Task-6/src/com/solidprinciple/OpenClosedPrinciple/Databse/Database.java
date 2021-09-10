package com.solidprinciple.OpenClosedPrinciple.Databse;


import com.solidprinciple.OpenClosedPrinciple.Invoice.Invoice;
import com.solidprinciple.OpenClosedPrinciple.Model.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Invoice> invoiceDb = new ArrayList<>();
    public static HashMap<String, Book> bookDb = new HashMap<>();
}