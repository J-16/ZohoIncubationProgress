package com.solidprinciple.singleresponsibilityprinciple.VIOLATE.Database;

import com.solidprinciple.singleresponsibilityprinciple.VIOLATE.Invoice;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Invoice> invoiceDb = new ArrayList<>();
    public static HashMap<String, Invoice> bookDb = new HashMap<>();
}
