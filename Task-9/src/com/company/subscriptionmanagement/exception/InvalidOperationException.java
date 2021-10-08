package com.company.subscriptionmanagement.exception;

public class InvalidOperationException extends RuntimeException{

    public enum  ExceptionType{
        BAD_REQUEST_EXCEPTION;
    }

    public InvalidOperationException(String exception){
        super(exception);
    }

    public int getStatusCode(){
        return 400;
    }

    public ExceptionType getExceptionType(){
        return ExceptionType.BAD_REQUEST_EXCEPTION;
    }

}
