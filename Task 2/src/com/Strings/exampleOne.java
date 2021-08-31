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

        /*
        *\ 1. When a string is created (String s = "random") JVM searches if "random" exists in string pool
        *       if exists then it give reference to s
        *       else a new string is created and gives reference to s
        *
        *  ( new ) a new string is created everytime (even duplicate exists or not in string pool or heap)
        * 2. When created (String s = new String("random") ) JVM creates "random" in heap memory and
        *       reference is given to s
        *
        * // Create via "new" and avoid duplicates
        *  4. When created with (String s = new String("random").intern())
        *       JVM searches for "random" in string pool
        *           if exists reference is returned
        *           else "random" is created and reference is returned
         */

    }
}