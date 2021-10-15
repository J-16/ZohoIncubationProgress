package com.company.subscriptionmanagement.view;

public class DisplayMessage {
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    public static void successMessage(String message){
        System.out.println(YELLOW + message + RESET);
    }

    public static void errorMessage(String error){
        System.err.println(error);
    }

    public static void menu(String menu){
        System.out.println(PURPLE + menu + RESET);
    }

    public static void list(String menu){
        System.out.println("\u001B[36m" + menu + RESET);
    }

}
