package com.company.subscriptionmanagement.database.Files;

import com.company.companiescustomer.model.Customer;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.exception.DatabaseException;
import com.company.subscriptionmanagement.model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserFile implements UserDB{

    private File subscriberFile = new File("subscriber.csv");
    private File companyFile = new File("company.csv");
    private File customerFile = new File("customer.csv");

    private FileWriter fileWriter;
    private Scanner scanner;

    private final String CSV_SEPARATOR = ",";

    //TODO : add id to customer and save as per that
    //TODO : check serializable

    @Override
    public HashMap<String, Subscriber> getSubscriber(){
        HashMap<String, Subscriber> subscribers = new HashMap<>();
        try{
            scanner = new Scanner(subscriberFile);
            while(scanner.hasNext()){
                String[] s = scanner.next().split(CSV_SEPARATOR);
                subscribers.put(s[0], new Subscriber(s[0],s[1]));
            }
        }catch (FileNotFoundException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
        return subscribers;
    }

    @Override
    public HashMap<String, Company> getCompanies(){
        HashMap<String, Company> companies = new HashMap<>();
        try{
            scanner = new Scanner(companyFile);
            //scanner.useDelimiter(CSV_SEPARATOR);
            while(scanner.hasNext()){
                String[] s = scanner.next().split(CSV_SEPARATOR);
                companies.put(s[0], new Company(s[0],s[1], s[2]));
            }
        }catch (FileNotFoundException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
        return companies;
    }

    @Override
    public void register(String name, String email, String password, UserDB.UserType userType){
        try {
            if(userType.equals(UserType.COMPANY))
                fileWriter = new FileWriter(companyFile, true);
            else if(userType.equals(UserType.CUSTOMER))
                fileWriter = new FileWriter(customerFile, true);
            fileWriter.append(name);
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append(email);
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append(password);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Company getUserByEmail(String email, UserDB.UserType userType){
        try{
            if(userType == UserType.CUSTOMER)
                scanner = new Scanner(customerFile);
            else
                scanner = new Scanner(companyFile);
            while(scanner.hasNext()){
                String[] s = scanner.next().split(CSV_SEPARATOR);
                if(s[1].equals(email)){
                    if(userType == UserType.CUSTOMER)
                        return new Customer(s[0],s[1],s[2]);
                    else
                        return new Company(s[0],s[1],s[2]);
                }
            }
        }catch (FileNotFoundException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
        return null;
    }

    @Override
    public Company getCompanyByName(String companyName){
        try{
            scanner = new Scanner(companyFile);
            while(scanner.hasNext()){
                String[] s = scanner.next().split(CSV_SEPARATOR);
                if(s[0].equals(companyName))
                    return new Company(s[0],s[1],s[2]);
            }
        }catch (FileNotFoundException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
        return null;
    }

    @Override
    public Company getCompanyByID(long ID) {
        return null;
    }

    @Override
    public void registerSubscriber(String email, String name){
        try{
            fileWriter = new FileWriter(subscriberFile, true);
            fileWriter.append(name);
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append(email);
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        }catch(IOException e){
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Subscriber getSubscribersByEmail(String email){
        try{
            scanner = new Scanner(subscriberFile);
            while(scanner.hasNext()){
                String[] s = scanner.next().split(CSV_SEPARATOR);
                if(s[1].equals(email))
                        return new Subscriber(s[0],s[1]);
            }
        }catch (FileNotFoundException e) {
            throw new DatabaseException("File not found", DatabaseException.ExceptionType.NOT_FOUND_EXCEPTION);
        }
        return null;
    }

    @Override
    public Subscriber getSubscribersByID(long ID) {
        return null;
    }


}
