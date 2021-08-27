package com.InnerClass.innerMethod;

class OuterClass{

    public void method(){
        //local to this method
        class methodInnerClass{
            public void method2(){
                System.out.println("inside method inner class");
            }
        }
        methodInnerClass methodInnerClass = new methodInnerClass();
        methodInnerClass.method2();
    }
}

public class exmapleOne {

    public static void main(String[] s){
        OuterClass outerClass = new OuterClass();
        outerClass.method();
    }

}
