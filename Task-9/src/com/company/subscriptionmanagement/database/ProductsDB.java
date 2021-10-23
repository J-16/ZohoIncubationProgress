package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Product;

import java.util.ArrayList;

public interface ProductsDB{
    void save(Product issue);
    void update(Product updateProduct);
    ArrayList<Product> getProducts();
    Product getByID(long ID);
}
