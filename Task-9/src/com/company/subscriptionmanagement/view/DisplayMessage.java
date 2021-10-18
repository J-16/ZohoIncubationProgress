package com.company.subscriptionmanagement.view;

public class DisplayMessage {
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";

    public static void successMessage(String message){
        System.out.println(YELLOW + message + RESET);
    }

    public static void errorMessage(String error){
        System.err.println(error);
    }

    public static void border(String menu){
        System.out.print( BLUE + menu + RESET);
    }

    public static void menu(String menu){
        System.out.println(PURPLE + menu + RESET);
    }

    public static void listHeading(String menu){
        System.out.print( RED + menu + RESET);
    }

}
