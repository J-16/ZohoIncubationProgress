package com.DataStructures;


import java.util.HashMap;

public class hashMaps {
    public static void main(String[] arg){
        HashMap<Integer, String> hashMap = new HashMap<>();

        //Insert - O(N) avg - O(1)
        hashMap.put(1,"value1");
        hashMap.put(2,"value2");

        //Remove - O(N) avg - O(1)
        hashMap.remove(1);

        //get - O(N) avg - O(1)
        System.out.println( hashMap.get(2) );

    }
}
