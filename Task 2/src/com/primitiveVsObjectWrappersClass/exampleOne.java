package com.primitiveVsObjectWrappersClass;

import java.util.ArrayList;

class PrimitiveType{
    //these values get 0 as default value (0.0f, 0.0d, '\u0000', false)
    //cannot be used in Collections and generics
    //ArrayList<int> arraylist = new ArrayList<int>(); // not possible as they only accept object (Generics)
    public void method(){
        byte b = 127;
        short s = 2;
        int  i = 10;
        char c = 'a';
        boolean bo = true;
        float f = 0.2f;
        double d = 3.334d;
    }
}

class Wrappers{
    //default value will be null as these are objects
    //these area final (Immutable)
    // Used in Collections
    ArrayList<Integer> arraylist = new ArrayList<>();
    public void method(){
        Byte b = 127;
        Short s = 2;
        Integer  i = 10;
        Character c = 'a';
        Boolean bo = true;
        Float f = 0.2f;
        Double d = 3.334d;
    }
}

public class exampleOne {

    public static void main(String[] args){

    }

}
