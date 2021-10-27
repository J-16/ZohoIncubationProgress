package com.company.subscriptionmanagement.database.Files;

import com.company.subscriptionmanagement.database.ProductsDB;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProductsFile implements ProductsDB{

    private File productFile = new File("products.csv");
    private File productFile2 = new File("products2.csv");
    private FileWriter fileWriter;
    private final String CSV_SEPARATOR = ",";

    private FileWriter getFIle() throws IOException {
        if(productFile.length() > 0)
            return new FileWriter(productFile, true);
        return new FileWriter(productFile2, true);
    }

    public void save(Product product){
        try {
            fileWriter = getFIle();
            fileWriter.append((char) product.getID());
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append(product.getProductName());
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append((char) product.getCompanyID());
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append((char) product.getTrailDays());
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append((char) product.getPrice());
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append( product.isTrailAvailable() ? "yes" : "no");
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public void update(Product updateProduct){

    }

    @Override
    public ArrayList<Product> getProductsByCompanyID(long companyID) {
        return null;
    }

    @Override
    public Product getByID(long ID) {
        return null;
    }

}
