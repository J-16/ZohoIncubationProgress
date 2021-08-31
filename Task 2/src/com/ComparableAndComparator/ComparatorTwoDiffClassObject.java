package com.ComparableAndComparator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class StudentsNew implements studentsInterface{
    private int rollNo;
    private String name;
    private int total;

    public StudentsNew(int rollNo, String name, int total){
        this.rollNo = rollNo;
        this.name = name;
        this.total= total;
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

class CustomTotalComparator implements Comparator<studentsInterface>{
    @Override
    public int compare(studentsInterface o1, studentsInterface o2) {

        Students students = o1 instanceof Students ? (Students) o1 : null;
        StudentsNew studentsNew = o1 instanceof StudentsNew ? (StudentsNew) o1 : null;

        Students students1 = o2 instanceof Students ? (Students) o2 : null;
        StudentsNew studentsNew1 = o2 instanceof StudentsNew ? (StudentsNew) o2 : null;

        int total1 = students != null ? students.getTotal() : studentsNew.getTotal();
        int total2 = students1 != null ? students1.getTotal() :  studentsNew1.getTotal();

        if( total1 > total2 )
            return 1;
        if( total1  < total2 )
            return -1;
        return 0;
    }
}

class CustomTotalComparatorDec implements Comparator<studentsInterface>{
    @Override
    public int compare(studentsInterface o1, studentsInterface o2) {

        Students students = o1 instanceof Students ? (Students) o1 : null;
        StudentsNew studentsNew = o1 instanceof StudentsNew ? (StudentsNew) o1 : null;

        Students students1 = o2 instanceof Students ? (Students) o2 : null;
        StudentsNew studentsNew1 = o2 instanceof StudentsNew ? (StudentsNew) o2 : null;

        int total1 = students != null ? students.getTotal() : studentsNew.getTotal();
        int total2 = students1 != null ? students1.getTotal() :  studentsNew1.getTotal();

        if( total1 > total2 )
            return -1;
        if( total1  < total2 )
            return 1;
        return 0;
    }
}

public class ComparatorTwoDiffClassObject {

    public static void main(String[] args){
        ArrayList<studentsInterface> students = new ArrayList<>();
        students.add(new StudentsNew(2001, "student11", 100));
        students.add(new StudentsNew(2004, "student14",91));
        students.add(new StudentsNew(2003, "student13",92));
        students.add(new StudentsNew(2002, "student12",93));
        students.add(new Students(1001, "student1", 90));
        students.add(new Students(1004, "student4",95));
        students.add(new Students(1003, "student3",98));
        students.add(new Students(1002, "student2",93));

        for (studentsInterface student : students){
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal() );
        }

        System.out.println("-------");
        CustomTotalComparator customComparator = new CustomTotalComparator();
        Collections.sort(students, customComparator);
        for (studentsInterface student : students)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

        System.out.println("-------");
        CustomTotalComparatorDec customComparator1 = new CustomTotalComparatorDec();
        Collections.sort(students, customComparator1);
        for (studentsInterface student : students)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

    }

}
