package com.ATM.Exceptions;

public class TransactionLimitException extends RuntimeException{
    public TransactionLimitException(String s){
        super(s);
    }
}
