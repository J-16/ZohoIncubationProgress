package com.solidprinciple.SingleResponsibilityPrinciple.Databse;

import com.solidprinciple.SingleResponsibilityPrinciple.Invoice.Invoice;
import com.solidprinciple.SingleResponsibilityPrinciple.Model.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Invoice> invoiceDb = new ArrayList<>();
    public static HashMap<String, Book> bookDb = new HashMap<>();
}