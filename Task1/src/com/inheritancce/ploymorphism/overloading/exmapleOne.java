package com.inheritancce.ploymorphism.overloading;

class Math{
    public static int max(int a, int b){
        return a > b ? a : b;
    }
    public static int max(int a, int b, int c){
        if(a > b && a > c )
            return a;
        if(b>a && b>c)
            return b;
        return c;
    }
}
public class exmapleOne {

    public static void main(String[] s){
        System.out.println( Math.max(5,6) );
        System.out.println( Math.max(5,6,7) );
    }

}
