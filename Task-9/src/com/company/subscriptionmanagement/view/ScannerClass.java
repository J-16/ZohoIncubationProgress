package com.company.subscriptionmanagement.view;

import java.util.Scanner;

public class ScannerClass{

    private static Scanner scanner;

    private ScannerClass(){
    }

    public static Scanner getScanner(){
        if(scanner == null)
            scanner = new Scanner(System.in);
        return scanner;
    }

    public static void closeScanner(){
        scanner.close();
    }

}