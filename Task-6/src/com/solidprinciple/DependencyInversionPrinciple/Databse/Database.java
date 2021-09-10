package com.solidprinciple.DependencyInversionPrinciple.Databse;


import com.solidprinciple.DependencyInversionPrinciple.Invoice.Invoice;
import com.solidprinciple.DependencyInversionPrinciple.Model.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Invoice> invoiceDb = new ArrayList<>();
    public static HashMap<String, Book> bookDb = new HashMap<>();
}