package com.company.subscriptionmanagement.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GetValues {

    private static Scanner sc = new Scanner(System.in);

    public static int getIntegerValue(int minLimit, String message){
        int value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.print("Invalid input, ");
                sc.nextLine();
            }
        }
        return value;
    }

    public static double getDoubleValue(int minLimit, String message){
        int value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.print("Invalid input, ");
                sc.nextLine();
            }
        }
        return value;
    }

    public static long getLongValue(int minLimit, String message){
        long value = -1;
        while(value < minLimit){
            try{
                System.out.println(message);
                value = sc.nextLong();
            }catch(InputMismatchException e){
                System.out.print("Invalid input, ");
                sc.nextLine();
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
                expDate = sc.next();
                LocalDate.parse(expDate);
                isDate = true;
            }catch(DateTimeParseException e){
                System.out.print("Incorrect date format, ");
                sc.nextLine();
            }
        }
        return expDate;
    }

}
