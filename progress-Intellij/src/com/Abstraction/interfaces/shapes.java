package com.Abstraction.interfaces;

interface Shape{
    void area();
    void perimeter();
}

class Circle implements Shape{
    private double radius;

    Circle(int radius){
        this.radius = radius;
    }

    public void area(){
        System.out.println(3.14 * radius * radius);
    }
    public void perimeter(){
        System.out.println( 2 * 3.14 * radius);
    }
}

class Rectangle implements Shape{
    private double length , breadth;

    Rectangle(double length, double breadth){
        this.length = length;
        this.breadth = breadth;
    }
    public void area(){
        System.out.println( 2 * (length + breadth) );
    }
    public void perimeter(){
        System.out.println( length * breadth );
    }
}

public class shapes {
    public static void main(String[] s) {

        Shape shape = new Circle(5);
        shape.area();
        shape.perimeter();

        shape = new Rectangle(4,5);
        shape.area();
        shape.perimeter();

    }
}

