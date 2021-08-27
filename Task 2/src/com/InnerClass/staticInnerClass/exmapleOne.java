package com.InnerClass.staticInnerClass;

class OuterClass{
    private static int x=10;
    int y=10; // inner class cannot access this as it is static
    static class InnerClass{
        int x=1;
        public void method(){
            System.out.println(x); // can access only static members
        }
    }
}

public class exmapleOne {
    public static void main(String[] args){
        OuterClass.InnerClass innerClass = new OuterClass.InnerClass();
        innerClass.method();
    }
}
