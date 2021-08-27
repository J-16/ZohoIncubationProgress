package com.classes;


class privateCheck{
    private void method(){
        System.out.println("method");
    }
    public void method1(){
        System.out.println("method1");
    }
}

class check extends privateCheck{

    public void method() {
        System.out.println("method");
    }

}


public class exampleTwo {

    public static void main(String[] args){
        privateCheck privateCheck = new privateCheck();
        privateCheck.method1();
        check check = new check();
        check.method();
    }

}
