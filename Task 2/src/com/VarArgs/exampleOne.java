package com.VarArgs;


class Math{

    public static int add(int ...values){
        int sum=0;
        for(int i =0;i<values.length; i++){
            sum += values[i];
        }
        return sum;
    }

    public static int multiply(int ...values){
        int sub = 1;
        if(values.length < 1) return 0;
        for(int i =0;i<values.length; i++){
            sub *= values[i];
        }
        return sub;
    }

}

public class exampleOne {

    public static void main(String ...args){
        System.out.println( Math.add(1) );
        System.out.println( Math.add(1,2,3) );
        System.out.println( Math.add(10,15,16,48,45,12) );

        System.out.println( Math.multiply(1) );
        System.out.println( Math.multiply(1,2,3) );
        System.out.println( Math.multiply(10,15,16,48,45,12) );
        System.out.println( Math.multiply() );
    }
}
