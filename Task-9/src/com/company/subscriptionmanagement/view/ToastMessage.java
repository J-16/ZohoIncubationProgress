package com.company.subscriptionmanagement.view;

public class ToastMessage {
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void SuccessMessage(String message){
        System.out.println(YELLOW + message + RESET);
    }
}
