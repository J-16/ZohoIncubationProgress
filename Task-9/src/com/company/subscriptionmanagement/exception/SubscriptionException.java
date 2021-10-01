package com.company.subscriptionmanagement.exception;

public class SubscriptionException extends RuntimeException{

    public SubscriptionException(){

    }

    public SubscriptionException(String exception){
        super(exception);
    }

}