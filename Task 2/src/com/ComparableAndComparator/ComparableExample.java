package com.ComparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface studentsInterface{
    int getrollNO();
    String getName();
    int getTotal();
}

class Students implements Comparable<Students>, studentsInterface{
    private int rollNo;
    private String name;
    private int total;

    public Students(int rollNo, String name, int total){
        this.rollNo = rollNo;
        this.name = name;
        this.total= total;
    }

    @Override
    public int compareTo(Students student) {
        return this.rollNo > student.rollNo ? 1 : -1;
    }

    public int getrollNO(){
        return rollNo;
    }
    public String getName(){
        return name;
    }
    public int getTotal(){
        return total;
    }
}

class CompareTotalInc implements Comparator<Students>{

    @Override
    public int compare(Students student1, Students student2) {
        if (student1.getTotal() > student2.getTotal())
            return 1;
        if (student1.getTotal() < student2.getTotal())
            return -1;
        return 0;
    }
}

class CompareTotalDec implements Comparator<Students>{

    @Override
    public int compare(Students student1, Students student2) {
        if (student1.getTotal() > student2.getTotal())
            return -1;
        if (student1.getTotal() < student2.getTotal())
            return 1;
        return 0;
    }
}

public class ComparableExample {

    public static void main(String[] args){

        ArrayList<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(1001, "student1", 90));
        studentsList.add(new Students(1004, "student4",95));
        studentsList.add(new Students(1003, "student3",98));
        studentsList.add(new Students(1002, "student2",96));

        for (Students student : studentsList){
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal() );
        }
        System.out.println("-------");
        Collections.sort(studentsList);
        for (Students student : studentsList)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

        System.out.println("-------");
        CompareTotalInc complexTotal = new CompareTotalInc();
        Collections.sort(studentsList,complexTotal);
        for (Students student : studentsList)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

        System.out.println("-------");
        CompareTotalDec complexTotaldec = new CompareTotalDec();
        Collections.sort(studentsList,complexTotaldec);
        for (Students student : studentsList)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

    }

}
