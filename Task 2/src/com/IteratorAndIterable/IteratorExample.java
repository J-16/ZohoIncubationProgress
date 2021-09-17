package com.IteratorAndIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class IteratorExample {

    public static void main(String ...args){
        String[] s = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        ArrayList<String> stringList = new ArrayList<>( Arrays.asList(s) );

        Iterator iterator = stringList.iterator();

        while(iterator.hasNext()){
            if(iterator.next().equals("five")){
                iterator.remove();
                break;
            }
        }

        iterator.forEachRemaining( (str)-> System.out.println(str) );

    }

}