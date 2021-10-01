package com.company.subscriptionmanagement.exception;

public class UserException extends RuntimeException{
    public UserException(){

    }
    public UserException(String exception){
        super(exception);
    }
}
