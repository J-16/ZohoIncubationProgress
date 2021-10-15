package com.company.subscriptionmanagement.view;

import com.company.subscriptionmanagement.model.service.Validity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class GetValues{

    public static int getIntegerValue(String message, String error){
        int value = 0;
        while(true){
            try{
                DisplayMessage.menu(message);
                value = ScannerClass.getScanner().nextInt();
                if(value < 0) {
                    DisplayMessage.errorMessage(error);
                }
                else return value;
            }catch(InputMismatchException e){
                DisplayMessage.errorMessage("Invalid input, Enter a number");
                ScannerClass.getScanner().nextLine();
            }
        }
    }

    public static double getDoubleValue(String message, String error){
        int value = 0;
        while(true){
            try{
                DisplayMessage.menu(message);
                value = ScannerClass.getScanner().nextInt();
                if(value < 0)
                    DisplayMessage.errorMessage(error);
                else return value;
            }catch(InputMismatchException e){
                DisplayMessage.errorMessage("Invalid input, Enter a number");
                ScannerClass.getScanner().nextLine();
            }
        }
    }

    public static long getLongValue(String message, String error){
        long value = -1;
        while(true){
            try{
                DisplayMessage.menu(message);
                value = ScannerClass.getScanner().nextLong();
                if(value < 0)
                    System.err.println(error);
                else return value;
            }catch(InputMismatchException e){
                DisplayMessage.errorMessage("Invalid input, Enter a number");
                ScannerClass.getScanner().nextLine();
            }
        }
    }

    public static String getDate(String message){
        String expDate = null;
        boolean isDate = false;
        while(!isDate){
            try{
                DisplayMessage.menu(message);
                expDate = ScannerClass.getScanner().next();
                LocalDate date = LocalDate.parse(expDate);
                if(date.isBefore(LocalDate.now()))
                    DisplayMessage.errorMessage("Enter valid date");
                else isDate = true;
            }catch(DateTimeParseException e){
                DisplayMessage.errorMessage("Incorrect date format");
            }
        }
        return expDate;
    }

    public static String getString(String message){
        DisplayMessage.menu(message);
        return ScannerClass.getScanner().next();
    }

    public static String getLine(String message){
        DisplayMessage.menu(message);
        ScannerClass.getScanner().nextLine();
        return ScannerClass.getScanner().nextLine();
    }

    public static String getEmail(String message){
        while(true){
            DisplayMessage.menu(message);
            String email = ScannerClass.getScanner().next();
            if(!Validity.isValidEmail(email))
                DisplayMessage.errorMessage("not a valid email id");
            else return email;
        }
    }

}
