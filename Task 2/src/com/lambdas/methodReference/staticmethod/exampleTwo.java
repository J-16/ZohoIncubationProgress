package com.lambdas.methodReference.staticmethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface Compare{
    int method(int x, int y);
}

class Math{

    public static int max(int x, int y){
        return x > y ? x : y;
    }

    public static int min(int x, int y){
        return x < y ? x : y;
    }

}

public class exampleTwo {

    public static void main(String[] args){

        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(8);
        list.add(4);
        list.add(5);

        System.out.println(  );

        for(int i=0;i<list.size();i+=2){

            Compare compare = Math::max;
            int max =  compare.method( list.get(i), list.get(i+1) );

        }

    }

}
