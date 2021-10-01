package com.company.subscriptionmanagement.exception;

public class InputException extends RuntimeException{
    public InputException(){
    }
    public InputException(String exception){
        super(exception);
    }
}
