package com.lambdas.methodReference.constructor;

import java.util.ArrayList;
import java.util.List;

class Student{

    private String name;
    private int rollNo;
    private static int count = 100;

    public Student(){
        name  = "dummyName" + count;
        rollNo = count++;
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

interface StudentCreationMethod<T>{
    T method();
}

public class exampleTwo {

    public static <T> List<T> createDummyStudent(int NoOfStd, StudentCreationMethod<T> obj){

        List<T> studentsList = new ArrayList<>();

        for(int i = 0 ; i < NoOfStd; i++ )
            studentsList.add( obj.method() );

        return studentsList;
    }

    public static void main(String[] s){

        List<Student> studentsList = createDummyStudent(10, Student::new);

        for(Student student : studentsList){
            System.out.println( student.getName() + " " +  student.getRollNo() );
        }

    }

}
