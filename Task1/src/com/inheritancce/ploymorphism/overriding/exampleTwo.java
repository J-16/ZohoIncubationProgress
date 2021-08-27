package com.inheritancce.ploymorphism.overriding;

class superClass{
    public void display(){
        System.out.println("In super");
    }
}

class sub extends superClass{
    public void display(){
        System.out.println("In sub");
    }
}

public class exampleTwo {
    public static void main(String[] args){
        superClass s = new sub();
        s.display(); // called based on the object and not based on reference;
    }
}
