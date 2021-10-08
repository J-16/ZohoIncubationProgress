package com.company.subscriptionmanagement.exception;

public class DatabaseException extends RuntimeException{

    public enum  ExceptionType{
        NOT_FOUND_EXCEPTION, EXISTS_EXCEPTION;
    }

    private ExceptionType exceptionType;
    private String field;

    public DatabaseException(String exception, ExceptionType exceptionType){
        super(exception);
        this.exceptionType = exceptionType;
    }

    public DatabaseException(String exception, ExceptionType exceptionType, String field){
        super(exception);
        this.exceptionType = exceptionType;
        this.field = field;
    }

    public ExceptionType getExceptionType(){
        return exceptionType;
    }

    public String getField(){
        return field;
    }

}
