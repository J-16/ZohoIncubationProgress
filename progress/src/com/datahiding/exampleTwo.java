package com.datahiding;

abstract class bankMethodAbstractClass{
    public abstract int withdraw(int amount);
    public abstract int deposit(int amount);
}

class newBank extends bankMethodAbstractClass{

    public int balance=0;

    public int withdraw(int amount){
        if(amount > 0){
            balance -= amount;
            return balance;
        }
        System.out.println("Invalid Amount");
        return balance;
    }
    public int deposit(int amount){
        if(amount > 0){
            balance += amount;
            return balance;
        }
        System.out.println("Invalid Amount");
        return balance;
    }

    public int getBalance(){
        return balance;
    }
}

public class exampleTwo {

    public static void main(String[] s){

        bankMethodAbstractClass user1 = new newBank();
        //user1.balance = 100; // not possible
        System.out.println(user1.deposit(100));
        //user1.getBalance(); // not possible

    }

}

