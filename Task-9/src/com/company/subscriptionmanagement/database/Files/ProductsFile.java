package com.company.subscriptionmanagement.database.Files;

import com.company.subscriptionmanagement.database.ProductsDB;
import com.company.subscriptionmanagement.model.Product;

import java.io.File;
import java.util.ArrayList;

public class ProductsFile implements ProductsDB{

    private File productFile = new File("products.csv");

    public void save(Product issue){

    }

    @Override
    public void update(Product updateProduct) {

    }

    public void update(){

    }

    public ArrayList<Product> getProducts(){
        return null;
    }

    @Override
    public Product getByID(long ID) {
        return null;
    }

}
