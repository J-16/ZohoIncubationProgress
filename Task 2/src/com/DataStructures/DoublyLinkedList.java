package com.DataStructures;

import java.util.LinkedList;
import java.util.List;

public class DoublyLinkedList {
    public static void main(String[] args){

        List<String> linkedList = new LinkedList<>();

        //Insert - O(1)
        linkedList.add("one");
        linkedList.add("three");
        linkedList.add("four");

        //Insert at - O(1)
        linkedList.add(2,"two");

        // remove - O(1)
        linkedList.remove("one");
        linkedList.remove(1);

        //get - O(n)
        System.out.println( linkedList.get(0) );
    }
}
