package com.lambdas.FunctionalInterface;

@FunctionalInterface
interface Math{
    void addition(int x, int y);
}

public class exampleOne{

    public static void main(String[] args){
        Math math = new Math(){
          public void addition(int x, int y){
              System.out.println(x+y);
          }
        };

        math.addition(9,5);
    }

}
