package com.company.SubscriptionManagement.Model;

import java.util.ArrayList;

public class Company implements ICompany{

    private final IPasswordAccount account;
    private ArrayList<Product> products;

    public Company(String name, String email, String password){
        this.account = new PasswordAccount(name,email, password);
        this.products = new ArrayList<>();
    }

    public IPasswordAccount getAccount(){
        return account;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products.add(products);
    }
}
