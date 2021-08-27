package com.InnerClass.Anonymous;

interface Anonymous {
    int getValue();
}

public class understanding {
    private int x=15;
    public static void main(String[] args){

     Anonymous anonymous = new Anonymous(){
         @Override
         public int getValue() {
             return 5;
         }
         public int getData(){
             return 5;
         }
     };

        understanding u = new understanding();
        System.out.println( u.x + anonymous.getValue() );
    }
}
