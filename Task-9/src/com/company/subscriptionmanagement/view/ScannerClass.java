package com.company.subscriptionmanagement.view;

import java.util.Scanner;

public class ScannerClass{

    private static Scanner scanner;

    private ScannerClass(){
        if(scanner == null)
            scanner = new Scanner(System.in);
    }

    public static Scanner getScanner(){
        return scanner;
    }

    public static void closeScanner(){
        scanner.close();
    }

}