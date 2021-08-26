package com.Abstraction.interfaces;

interface one{
    int l=12;
    void method();
}

interface two {
    void method2();
}

interface three extends one, two{
    void method3();
}

public class exampleOne implements three{

    public void method(){

    }
    public void method2(){

    }
    public void method3(){

    }

    public static void main (String[] s){
        System.out.println("Hello");
        System.out.println();
    }

}