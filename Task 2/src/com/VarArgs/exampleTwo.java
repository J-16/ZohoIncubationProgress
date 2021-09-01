package com.VarArgs;

class Sample{
    //public static int method(String ...args, int x){} // not possible as varargs must last parameter
    //public static int method(char c, String ... args, int ...a){} // same as above so not possible

    public static void method(char c, int ...i){
        System.out.println(c);
        for(int x : i)
            System.out.print(x + " ");
        System.out.println();
    }
    public static void method(int c, int ...i){
        System.out.println(c);
        for(int x : i)
            System.out.print(x + " ");
        System.out.println();
    }
    public static void method(String c, int ...i){
        System.out.println(c);
        for(int x : i)
            System.out.print(x + " ");
        System.out.println();
    }
}

public class exampleTwo {
    public static void main(String ...args){
        Sample.method('a', 1,2,3);
        Sample.method(3, 1,2,3);
        Sample.method("String", 1,2,3);
    }
}