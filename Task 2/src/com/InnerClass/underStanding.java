package com.InnerClass;

class OuterClass {

    private int y=10; // inner class can access

    class InnerCLass{

        public int x=10;

        public void method(){
            System.out.println(x + " " + y);
        }

        //Static methods are not allowed in inner class
//        public static int method2(){
//            System.out.println("Static method of inner class");
//        }
    }
    private class InnerClass2{ //can be used outside
        public int x;
    }

    public void method(){
        InnerCLass s = new InnerCLass();
        s.method();
        //System.out.println(x); // outer class can't access without object
    }

}

public class underStanding {

    public static void main(String[] s){
        OuterClass outerClass = new OuterClass();
        outerClass.method();
        OuterClass.InnerCLass innerCLass = outerClass.new InnerCLass();
        innerCLass.method();
        //above is not possible for InnerClass2
    }

}
