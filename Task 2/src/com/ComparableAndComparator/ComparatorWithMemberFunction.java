package com.ComparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;

class ComparatorMethod {
    public static int compareTotalDec(Students student1, Students student2) {
        if (student1.getTotal() > student2.getTotal())
            return 1;
        if (student1.getTotal() < student2.getTotal())
            return -1;
        return 0;
    }
    public static int compareTotalInc(Students student1, Students student2) {
        if (student1.getTotal() > student2.getTotal())
            return -1;
        if (student1.getTotal() < student2.getTotal())
            return 1;
        return 0;
    }
}

public class ComparatorWithMemberFunction {

    public static void main(String[] args){
        ArrayList<Students> students = new ArrayList<>();
        students.add(new Students(1001, "student1", 90));
        students.add(new Students(1004, "student4",95));
        students.add(new Students(1003, "student3",98));
        students.add(new Students(1002, "student2",96));

        for (Students student : students){
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal() );
        }
        System.out.println("-------");
        Collections.sort(students, ComparatorMethod::compareTotalDec);
        for (Students student : students)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

        System.out.println("-------");
        Collections.sort(students, ComparatorMethod::compareTotalInc);
        for (Students student : students)
            System.out.println( student.getrollNO() + " : " + student.getName() + " : " + student.getTotal());

    }

}
