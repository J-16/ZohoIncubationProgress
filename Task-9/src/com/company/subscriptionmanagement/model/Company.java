package com.company.subscriptionmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Company implements ICompany{

    private IPasswordAccount account;
    private ArrayList<Product> products;
    private LinkedList<Issue> issueQueue; //TODO: add company functionality (get the issue);
    private HashMap<LocalDate, ArrayList<CurrentSubscription> > autoRenewal;

    public Company(String name, String email, String password){
        this.account = new PasswordAccount(name,email, password);
        this.products = new ArrayList<>();
        this.issueQueue = new LinkedList<>();
    }

    public void setAccount(IPasswordAccount account) {
        this.account = account;
    }

    public IPasswordAccount getAccount(){
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

    public void setAutoRenewal(LocalDate date, ArrayList<CurrentSubscription> subscriber){
        this.autoRenewal.put(date,subscriber);
    }

    public HashMap<LocalDate, ArrayList<CurrentSubscription>> getAutoRenewal(){
        return this.autoRenewal;
    }

}
