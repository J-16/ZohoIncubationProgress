package com.Strings;

public class exampleOne {
    public static void main(String[] s){

        String str = "random";
        String str1 = new String("random");
        System.out.println(str + " " + str1);

        //creates new object and assigns to str and str1 and old object which has random exists;
        str = "random1";
        str1 = "random1";

        System.out.println(str + " " + str1);

    }
}