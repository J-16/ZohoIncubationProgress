package com.company.subscriptionmanagement.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class GetValues{

    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";

    public static int getIntegerValue(String message, String error){
        int value = 0;
        while(true){
            try{
                System.out.println(PURPLE + message + RESET);
                value = ScannerClass.getScanner().nextInt();
                if(value < 0) {
                    System.err.println(error);
                }
                else return value;
            }catch(InputMismatchException e){
                System.err.println("Invalid input, Enter a number");
                ScannerClass.getScanner().nextLine();
            }
        }
    }

    public static double getDoubleValue(String message, String error){
        int value = 0;
        while(true){
            try{
                System.out.println(PURPLE + message + RESET);
                value = ScannerClass.getScanner().nextInt();
                if(value < 0)
                    System.err.println(error);
                else return value;
            }catch(InputMismatchException e){
                System.err.println("Invalid input, Enter a number");
                ScannerClass.getScanner().nextLine();
            }
        }
    }

    public static long getLongValue(String message, String error){
        long value = -1;
        while(true){
            try{
                System.out.println(PURPLE + message + RESET);
                value = ScannerClass.getScanner().nextLong();
                if(value < 0)
                    System.err.println(error);
                else return value;
            }catch(InputMismatchException e){
                System.err.println("Invalid input, Enter a number");
                ScannerClass.getScanner().nextLine();
            }
        }
    }

    public static String getDate(String message){
        String expDate = null;
        boolean isDate = false;
        while(!isDate){
            try{
                System.out.println(PURPLE + message + RESET);
                expDate = ScannerClass.getScanner().next();
                LocalDate date = LocalDate.parse(expDate);
                if(date.isBefore(LocalDate.now()))
                    System.err.println("Enter valid date");
                else isDate = true;
            }catch(DateTimeParseException e){
                System.err.println("Incorrect date format");
            }
        }
        return expDate;
    }

    public static String getString(String message){
        System.out.println(PURPLE + message + RESET);
        return ScannerClass.getScanner().next();
    }

    public static String getLine(String message){
        System.out.println(PURPLE + message + RESET);
        ScannerClass.getScanner().nextLine();
        return ScannerClass.getScanner().nextLine();
    }

}
