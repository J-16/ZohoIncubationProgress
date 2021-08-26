package com.Abstraction.abstractclass;

abstract class Random{
    protected Random(){
        System.out.println("random");
    }
    abstract void method();
}

class one extends Random{
    public one(){
        System.out.println("one");
    }
    public void method(){
        System.out.println("Hello");
    }
}

public class exampleOne{

    public static void main(String[] args){
        one o = new one();
        o.method();
    }

}
