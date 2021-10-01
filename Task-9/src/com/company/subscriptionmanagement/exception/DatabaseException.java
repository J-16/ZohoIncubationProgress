package com.company.subscriptionmanagement.exception;

public class DatabaseException extends RuntimeException{

    public DatabaseException(){

    }

    public DatabaseException(String exception){
        super(exception);
    }

}
