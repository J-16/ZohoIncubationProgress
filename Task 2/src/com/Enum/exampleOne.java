package com.Enum;

//TODO: ENUM

enum Day{
    //no modifiers
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
}

class ToDo{
    Day day;

    public ToDo(Day day){
        this.day = day;
    }

    public void todolist(){
        switch(day){
            case MONDAY:
                System.out.println("Task-1");
                break;
            case TUESDAY:
                System.out.println("Task-2");
                break;
            case WEDNESDAY:
                System.out.println("Task-3");
                break;
            case THURSDAY:
                System.out.println("Task-4");
                break;
            case FRIDAY:
                System.out.println("Task-5");
                break;
        }
    }
}

public class exampleOne {

    public static void main(String... args){
        ToDo todo = new ToDo(Day.MONDAY);
        todo.todolist();
    }

}
