package com.solidprinciple.InterfaceSegregationPrinciple.Databse;


import com.solidprinciple.InterfaceSegregationPrinciple.Interface.Item;
import com.solidprinciple.InterfaceSegregationPrinciple.Invoice.Invoice;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Invoice> invoiceDb = new ArrayList<>();
    public static HashMap<String, Item> bookDb = new HashMap<>();
}