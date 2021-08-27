package com.classes;

import java.util.Arrays;
import java.util.Collections;

class Student{

    private int rollNo;
    private Integer maths[] = new Integer[3], cs[] = new Integer[3];
    private String name;

    public Student(String name, int rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }

    public void display(){
        System.out.println("Name: "+ name + " rollNo: " + rollNo);
    }

    public void setCIA1Mark(int maths, int cs){
        this.maths[0] = maths;
        this.cs[0] = cs;
    }

    public void setCIA2Mark(int maths, int cs){
        this.maths[1] = maths;
        this.cs[1] = cs;
    }

    public void setCIA3Mark(int maths, int cs){
        this.maths[2] = maths;
        this.cs[2] = cs;
    }

    public void total(){
        System.out.println( name + " Total: " + (Collections.max(Arrays.asList(maths)) + Collections.max(Arrays.asList(cs))) );
    }

}

class exampleOne{
    public static void main( String[] s ){
        Student student = new Student("abishek", 1001);
        Student student1 = new Student("bala", 1002);

        student.setCIA1Mark(40, 40);
        student.setCIA2Mark(50, 40);
        student.setCIA3Mark(50, 50);

        student1.setCIA1Mark(40, 40);
        student1.setCIA2Mark(50, 40);
        student1.setCIA3Mark(50, 50);

        student.total();
        student1.total();

    }

}