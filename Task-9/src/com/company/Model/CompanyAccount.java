package com.company.Model;

import java.util.ArrayList;

public class CompanyAccount {
    private final IAccount account;
    private ArrayList<Product> products;

    public CompanyAccount(String name, String email, String password){
        this.account = new Account(name,email, password);
        this.products = new ArrayList<>();
    }

    public IAccount getAccount(){
        return account;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products.add(products);
    }
}
