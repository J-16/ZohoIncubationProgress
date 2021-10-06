package com.company.subscriptionmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface ICompany{

    PasswordAccount getAccount();
    ArrayList<Product> getProducts();
    void setProducts(Product products);
    void setAutoRenewal(HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal);
    HashMap<LocalDate, LinkedList<CurrentSubscription>> getAutoRenewal();
    void addIssue(Issue issue);
    Issue getIssue();

}