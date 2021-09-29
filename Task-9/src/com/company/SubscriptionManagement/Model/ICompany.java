package com.company.SubscriptionManagement.Model;

import java.util.ArrayList;

public interface ICompany{
    IPasswordAccount getAccount();
    ArrayList<Product> getProducts();
    void setProducts(Product products);
}
