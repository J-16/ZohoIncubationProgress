package com.lambdas.methodReference.constructor;

interface RandomInterface{
    void method();
}

class example{
    public example(){
        System.out.println("Constructor reference");
    }
}

public class exampleOne {

    public static void main(String[] args){
        RandomInterface randomInterface = example::new;
        randomInterface.method();
    }

}