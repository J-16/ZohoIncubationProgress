package com.lambdas.methodReference.constructor;

import java.util.ArrayList;
import java.util.List;

class Student{

    private String name;
    private int rollNo;

    public Student(int rollNo, String name){
        this.name  = name;
        this.rollNo = rollNo;
    }

    public void setName(){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public int getRollNo(){
        return rollNo;
    }
}

interface StudentCreator<T>{
    List method();
}

public class exampleTwo {

    public static <T> List<T> createDummyStudent(List<T> copyFrom){

        List<T> studentsList = new ArrayList<>();

        return studentsList;
    }

    public static void main(String[] s){

        List<Student> studentsList = new ArrayList<Student>();
        studentsList.add(new Student(100, "stud1"));
        studentsList.add(new Student(101, "stud2"));
        studentsList.add(new Student(102, "stud3"));
        studentsList.add(new Student(103, "stud4"));


        for(Student student : studentsList){
            System.out.println( student.getName() + " " +  student.getRollNo() );
        }

    }

}
