package com.company.subscriptionmanagement.exception;

public class DatabaseException extends RuntimeException{

    public enum  ExceptionType{
        NOT_FOUND_EXCEPTION, EXISTS_EXCEPTION
    }

    private ExceptionType exceptionType;

    public DatabaseException(String exception, ExceptionType exceptionType){
        super(exception);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType(){
        return exceptionType;
    }

}
