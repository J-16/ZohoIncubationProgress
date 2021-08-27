package com.InnerClass.MethodLocalInnerClass;

class Math{

    public void division(double dividend, double divisor){

        if( dividend > 0 && divisor > 0 ){

            class DivisorClass{
                private double quotient;
                private double remainder;
                public double getRemainder(){
                    return dividend%divisor;
                }
                public double getQuotient(){
                    return dividend / divisor;
                }
            }

            DivisorClass divisorClass = new DivisorClass();
            System.out.println( divisorClass.getRemainder() );
            System.out.println( divisorClass.getQuotient() );

        }
    }
}

public class exampleTwo {
    public static void main(String[] args){
        new Math().division(5,2);
    }
}
