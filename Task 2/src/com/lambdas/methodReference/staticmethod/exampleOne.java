package com.lambdas.methodReference.staticmethod;

import java.util.ArrayList;

interface lambdaMethod{
    String method(String s);
}

class ListOfMethods{

    public static String reverse(String x){
        StringBuilder sb = new StringBuilder(x);
        sb.reverse();
        return sb.toString();
    }

    public static String toLowerCase(String x){
        return x.toLowerCase();
    }

    public static String removeWhiteSpace(String x){
        return x.trim();
    }

}

public class exampleOne {

    public static void main(String[] args){

        lambdaMethod lambdaMethod;

        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        for(String s : list){
            lambdaMethod = ListOfMethods::toLowerCase;
            s = lambdaMethod.method(s);

            lambdaMethod = ListOfMethods::reverse;
            s = lambdaMethod.method(s);

            lambdaMethod = ListOfMethods::removeWhiteSpace;
            s = lambdaMethod.method(s);

            System.out.println(s);
        }
    }
}

