package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.ProductsDB;
import com.company.subscriptionmanagement.model.Product;

import java.util.ArrayList;

public class ProductsDS implements ProductsDB{

    private ArrayList<Product> products;

    public ProductsDS(){
        products = new ArrayList<>();
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product updateProduct){
        for(Product product : this.products){
            if( product.getID() == updateProduct.getID() )
                products.remove(product);
        }
        products.add(updateProduct);
    }

    @Override
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public Product getByID(long ID) {
        for(Product product : this.products){
            if( product.getID() == ID )
                return product;
        }
        return null;
    }
}
