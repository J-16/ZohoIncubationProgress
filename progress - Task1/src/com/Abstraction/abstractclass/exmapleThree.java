package com.Abstraction.abstractclass;

abstract class Shape{
    String shape;
    Shape(String shape){
        this.shape = shape;
    }
    public abstract void area();
    public abstract void perimeter();
    public void display(){
        System.out.print(shape + "  ");
    }
}

class Circle extends Shape {
    private double radius;
    Circle(int radius){
        super("Circle");
        this.radius = radius;
    }

    public void area(){
        display();
        System.out.println( "area: " +  3.14 * radius * radius);
    }
    public void perimeter(){
        display();
        System.out.println( "perimeter: " + 2 * 3.14 * radius);
    }
}

class Rectangle extends Shape {
    private double length , breadth;

    Rectangle(double length, double breadth){
        super("Rectangle");
        this.length = length;
        this.breadth = breadth;
    }
    public void area(){
        display();
        System.out.println( "area: " + 2 * (length + breadth) );
    }
    public void perimeter(){
        display();
        System.out.println( "perimeter: " +length * breadth );
    }
}

public class exmapleThree {
    public static void main(String[] s) {

        Circle shape = new Circle(5);
        shape.area();
        shape.perimeter();

        Rectangle shape1 = new Rectangle(4,5);
        shape1.area();
        shape1.perimeter();

    }
}
