package com.company.SubscriptionManagement.Exception;

public class SubscriptionException extends RuntimeException{
    public SubscriptionException(){
    }
    public SubscriptionException(String exception){
        super(exception);
    }
}
