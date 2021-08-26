package com.inheritancce;

class Bicycle{
    private int speed;

    public Bicycle(){
        speed = 0;
    }
    public void applyBreak(){
        if(speed > 0)
            speed--;
    }
    public void peddleUp(){
        speed++;
    }
    protected int getSpeed(){
        return speed;
    }
    public void display(){
        System.out.println("Speed: " + speed);
    }
}

class GearCycle extends Bicycle{
    private int gear;

    public GearCycle(int gear){
        this.gear = gear;
    }

    public void changeGear(int gear){
        if(gear > 0 && gear < 5)
            this.gear = gear;
    }

    @Override
    public void display(){
        System.out.println("Speed : " + getSpeed() + " Gear: " + gear);
    }
}

public class exampleTwo {
    public static void main(String[] s){
        Bicycle bicycle = new Bicycle();
        bicycle.peddleUp();
        bicycle.peddleUp();
        bicycle.peddleUp();
        bicycle.display();

        GearCycle gearCycle = new GearCycle(2);
        gearCycle.peddleUp();
        gearCycle.peddleUp();
        gearCycle.changeGear(4);
        gearCycle.display();
    }
}
