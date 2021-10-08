package com.company.subscriptionmanagement.exception;

public class InputException extends RuntimeException{

    public enum  ExceptionType{
        EMPTY_EXCEPTION, NEGATIVE_VALUE, INVALID_FORMAT;
    }

    private ExceptionType exceptionType;
    private String field;

    public InputException(String exception, ExceptionType exceptionType, String field){
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