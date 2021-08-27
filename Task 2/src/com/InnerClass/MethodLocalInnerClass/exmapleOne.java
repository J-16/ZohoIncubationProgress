package com.InnerClass.MethodLocalInnerClass;

class OuterClass{

    {
        class InnerBlockClass{
            private void method2(){
                if(true){
                    class InnerIFClass{
                        public void method(){
                            System.out.println("inside if block");
                        }
                    }
                    InnerIFClass innerIFClass = new InnerIFClass();
                    innerIFClass.method();
                }
            }
            public void method(){
                System.out.println("inner block class");
                method2();
            }
        }
        InnerBlockClass innerBlockClass = new InnerBlockClass();
        innerBlockClass.method();
    }

    public void method(){
        int x = 20;
        //local to this method
        class methodInnerClass{
            public void method2(){
                System.out.println("inside method inner class");
                System.out.println(x);
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
