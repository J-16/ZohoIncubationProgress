package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Product;

import java.util.ArrayList;

public interface ProductsDB{
    void save(Product issue);
    void update(Product updateProduct);
    ArrayList<Product> getProductsByCompanyID(long companyID);
    Product getByID(long ID);
}
