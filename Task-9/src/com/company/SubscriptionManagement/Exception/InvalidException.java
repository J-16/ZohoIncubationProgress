package com.company.SubscriptionManagement.Exception;

public class InvalidException extends RuntimeException{
    public InvalidException(){

    }
    public InvalidException(String exception){
        super(exception);
    }
}
