package com.constructor;

class Random{
    private int x;
    public Random(){
        System.out.println("in random " + x);
    }
    public Random(int x){
        this.x = x;
        System.out.println("in random " + x);
    }
}

public class exampleOne {
    public static void main(String[] args){
        Random random = new Random();
        Random random1 = new Random(5);
    }
}