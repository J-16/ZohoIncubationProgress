package com.CollectionFrameWork;


import java.util.ArrayList;

public class exampleOne {
    public static void main(String[] args){
        ArrayList<Integer> arrayList = new ArrayList<>();

        //Insert - O(1)
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(6);

        //InsertAt - O(n)
        arrayList.add(4,5);

        //remove - O(n)
        arrayList.remove(2);

        //access - O(1)
        for(int i : arrayList)
            System.out.println(i);

    }
}
