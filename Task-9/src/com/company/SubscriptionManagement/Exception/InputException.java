package com.company.SubscriptionManagement.Exception;

public class InputException extends RuntimeException{
    public InputException(){
    }
    public InputException(String exception){
        super(exception);
    }
}
