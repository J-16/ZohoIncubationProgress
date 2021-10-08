package com.company.subscriptionmanagement.exception;

public class InvalidOperationException extends RuntimeException{

    public enum  ExceptionType{
        AUTHORIZATION_EXCEPTION, ILLEGAL_EXCEPTION;
    }

    public InvalidOperationException(String exception){
        super(exception);
    }

}
