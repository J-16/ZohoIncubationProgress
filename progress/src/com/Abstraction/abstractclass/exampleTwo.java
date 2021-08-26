package com.Abstraction.abstractclass;

abstract class VariableTest{
    private int x;
    public static final int X=10;
    public VariableTest(int x){
        this.x = x;
        System.out.println("Assigned in abstract class");
    }

    public void method(){
        System.out.println(x);
    }
    public abstract void method2();
}

class Test extends VariableTest{
    private int x;
    public Test(int x){
        super(x+1);
        this.x=x;
        System.out.println("Assigned to subclass");
    }
    public void method2(){
        System.out.println(x);
        System.out.println(super.X +" or "+ this.X);
    }
}

public class exampleTwo {
    public static void main(String[] args){
        Test test = new Test(5);
        test.method();
        test.method2();
        System.out.println(VariableTest.X);
    }
}
