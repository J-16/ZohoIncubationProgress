package com.company.SubscriptionManagement.Exception;

public class UserException extends RuntimeException{
    public UserException(){

    }
    public UserException(String exception){
        super(exception);
    }
}
