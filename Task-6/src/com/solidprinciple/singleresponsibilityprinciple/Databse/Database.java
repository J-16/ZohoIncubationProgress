package com.solidprinciple.singleresponsibilityprinciple.Databse;


import com.solidprinciple.singleresponsibilityprinciple.Invoice.Invoice;
import com.solidprinciple.singleresponsibilityprinciple.Model.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Invoice> invoiceDb = new ArrayList<>();
    public static HashMap<String, Book> bookDb = new HashMap<>();
}
