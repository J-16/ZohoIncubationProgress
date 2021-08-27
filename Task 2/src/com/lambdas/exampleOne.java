package com.lambdas;


interface Math{
    void addition(int x, int y);
}

interface Math1{
    int subtraction(int x, int y);
}

public class exampleOne {
    public static void main(String[] args){

        Math math = (int x, int y)->{System.out.println(x+y);};
        math.addition(4,5);

        System.out.println("---");

        Math math1 = (x, y)->{System.out.println(x+y);};
        math1.addition(4,5);

        Math1 m = (x,y)->x-y;
        System.out.println(m.subtraction(8,8));
    }
}