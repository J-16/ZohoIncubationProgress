package com.company.subscriptionmanagement.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class GetValues{

    public static int getIntegerValue(int minLimit, String message){
        int value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = ScannerClass.getScanner().nextInt();
                if(value < minLimit)
                    System.out.println("Enter a valid input");
            }catch(InputMismatchException e){
                System.out.println("Invalid input,");
                ScannerClass.getScanner().nextLine();
            }
        }
        return value;
    }

    public static double getDoubleValue(int minLimit, String message){
        int value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = ScannerClass.getScanner().nextInt();
                if(value < minLimit)
                    System.out.println("Enter a valid input");
            }catch(InputMismatchException e){
                System.out.println("Invalid input, ");
                ScannerClass.getScanner().nextLine();
            }
        }
        return value;
    }

    public static long getLongValue(int minLimit, String message){
        long value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = ScannerClass.getScanner().nextLong();
                if(value < minLimit)
                    System.out.println("Enter a valid input");
            }catch(InputMismatchException e){
                System.out.println("Invalid input, ");
                ScannerClass.getScanner().nextLine();
            }
        }
        return value;
    }

    public static String getDate(String message){
        String expDate = null;
        boolean isDate = false;
        while(!isDate){
            try{
                System.out.print(message);
                expDate = ScannerClass.getScanner().next();
                LocalDate date = LocalDate.parse(expDate);
                if(date.isBefore(LocalDate.now()))
                    System.out.println("Enter valid date");
                else isDate = true;
            }catch(DateTimeParseException e){
                System.out.println("Incorrect date format, ");
            }
        }
        return expDate;
    }

    public static String getString(String message){
        System.out.println(message);
        return ScannerClass.getScanner().next();
    }

    public static String getLine(String message){
        System.out.println(message);
        ScannerClass.getScanner().nextLine();
        return ScannerClass.getScanner().nextLine();
    }

}
