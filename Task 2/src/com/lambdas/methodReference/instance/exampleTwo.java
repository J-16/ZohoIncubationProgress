package com.lambdas.methodReference.instance;

import java.util.ArrayList;

interface lambdaMethod{
    String method(String s);
}

class ListOfMethods{

    public String reverse(String x){
        StringBuilder sb = new StringBuilder(x);
        sb.reverse();
        return sb.toString();
    }

    public String toLowerCase(String x){
        return x.toLowerCase();
    }

    public String removeWhiteSpace(String x){
        return x.trim();
    }

}

public class exampleTwo {

    public static void main(String[] args){
        ListOfMethods listOfMethods = new ListOfMethods();
        lambdaMethod lambdaMethod;

        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        for(String s : list){
            lambdaMethod = listOfMethods::toLowerCase;
            s = lambdaMethod.method(s);

            lambdaMethod = listOfMethods::reverse;
            s = lambdaMethod.method(s);

            lambdaMethod = listOfMethods::removeWhiteSpace;
            s = lambdaMethod.method(s);

            System.out.println(s);
        }
    }
}
