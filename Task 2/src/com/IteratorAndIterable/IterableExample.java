package com.IteratorAndIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class IterableExample {

    public static void main(String ...args){
        String[] s = {"one", "two", "three", "four", "five"};
        ArrayList<String> stringList = new ArrayList<>( Arrays.asList(s) );

        stringList.forEach( (str) -> System.out.println(str) );

        for(String str : s)
            System.out.println(str);

        //only for list
        ListIterator<String> listIterator = stringList.listIterator();

        while(listIterator.hasNext()){
            System.out.println(listIterator.next());
        }

        while(listIterator.hasPrevious())
            System.out.println(listIterator.previous());

    }

}
