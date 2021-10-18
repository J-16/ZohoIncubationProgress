package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.exception.InputException;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validity{

    public static void emptyCheck(String paramOneName, String paramOne, String paramTwoName, String paramTwo){
        if(paramOne == null || paramOne.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, paramOneName);
        if(paramTwo == null || paramTwo.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, paramTwoName);
    }

    public static void emptyCheck(String paramOneName, String paramOne){
        if(paramOne == null || paramOne.isEmpty())
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, paramOneName);
    }

    public static void nullCheck(String paramOneName, LocalDate date){
        if(date == null)
            throw new InputException("Empty fields are not allowed", InputException.ExceptionType.EMPTY_EXCEPTION, paramOneName);
    }

    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[a-z1-9]+[@][a-z]+[.][a-z]+$");
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        return password.length() > 7;
    }

}