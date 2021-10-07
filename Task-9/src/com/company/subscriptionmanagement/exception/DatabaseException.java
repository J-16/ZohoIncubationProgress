package com.company.subscriptionmanagement.exception;

public class DatabaseException extends RuntimeException{

    private ExceptionType error;

    public DatabaseException(String exception, ExceptionType exceptionType){
        super(exception);
        this.error = exceptionType;
    }

    public ExceptionType getError(){
        return error;
    }

}
