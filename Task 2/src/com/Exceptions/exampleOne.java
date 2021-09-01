package com.Exceptions;

class Example{

    // Exception is thrown from method2() and searches in method1() and thrown back to method() where it is handled
    public void method(){
        try{
            method1();
        }catch(Exception e){
            System.out.println("Exception occurred");
            throw e;
        }
    }

    public void method1(){
        method2();
    }

    public void method2(){
        int x = 1/0;
    }

}

class Math{

    public static int divide(int x, int y){
        int result = 0;
        try{
            result = x/y;
        }catch(ArithmeticException e){
            System.out.println("invalid number");
        }
        return result;
    }

    public static int divide1(int x, int y){
        return x/y;
    }

}

public class exampleOne {

    public static void main(String[] args){

        System.out.println(Math.divide(1,0));

        try {
            System.out.println(Math.divide1(1,0));
        }catch(ArithmeticException e){
            System.out.println("Invalid number");
        }

        try {
            new Example().method();
        }catch(Exception e){
            System.out.println(e);
        }

    }

}
