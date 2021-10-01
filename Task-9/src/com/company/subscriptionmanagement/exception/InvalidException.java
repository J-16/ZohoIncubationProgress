package com.company.subscriptionmanagement.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(){

    }
    public InvalidException(String exception){
        super(exception);
    }
}
