package com.DataStructures;

import java.util.LinkedList;

public class DoublyLinkedList {
    public static void main(String[] args){

        LinkedList<String> linkedList = new LinkedList<>();

        //Insert - O(n)
        linkedList.add("one");
        linkedList.add("three");
        linkedList.add("four");

        //Insert at - O(n)
        linkedList.add(2,"two");

        // remove - O(n)
        linkedList.remove("one");
        linkedList.remove(1);

        //get - O(n)
        System.out.println( linkedList.get(0) );

    }
}
