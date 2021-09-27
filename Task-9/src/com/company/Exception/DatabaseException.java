package com.company.Exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String exception){
        super(exception);
    }
}
