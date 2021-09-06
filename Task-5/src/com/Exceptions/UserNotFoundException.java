package com.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Invalid ATM card");
    }
}
