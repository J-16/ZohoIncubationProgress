package com.company.subscriptionmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Company implements Users {

    private ProtectedAccount account;
    private ArrayList<Product> products;
    private LinkedList<Issue> issueQueue; //TODO: add company functionality (get the issue);
    private HashMap<LocalDate, LinkedList<CurrentSubscription> > autoRenewal;

    public Company(String name, String email, String password){
        this.account = new ProtectedAccount(name,email,password);
        this.products = new ArrayList<>();
        this.issueQueue = new LinkedList<>();
        this.autoRenewal = new HashMap<>();
    }

    public void setAccount(Account account) {
        this.account = (ProtectedAccount) account;
    }

    public ProtectedAccount getAccount(){
        return account;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public void setProducts(Product products){
        this.products.add(products);
    }

    public void addIssue(Issue issue){
        issueQueue.addLast(issue);
    }

    public Issue getIssue(){
        return issueQueue.removeFirst();
    }

    public void setAutoRenewal(LocalDate date, LinkedList<CurrentSubscription> subscriber){
        this.autoRenewal.put(date,subscriber);
    }

    public void setAutoRenewal( HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal ){
        this.autoRenewal= autoRenewal;
    }

    public HashMap<LocalDate, LinkedList<CurrentSubscription>> getAutoRenewal(){
        return this.autoRenewal;
    }

}
