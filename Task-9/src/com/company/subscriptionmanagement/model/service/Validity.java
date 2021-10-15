package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.exception.InputException;

import java.util.regex.Pattern;

public class Validity{

    public static void emptyCheck(String email, String password){
        if(email.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, "email");
        if(password.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, "password");
    }

    public static void emptyCheck(String name){
        if(name.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, "name");
    }

    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[a-z1-9]+@[a-z]+.[a-z]+$");
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        return password.length() > 7;
    }

}