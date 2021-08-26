package com.datahiding;

class newBank2{

    private int balance=0;

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

public class exampleThree {

    public static void main(String[] s){

        newBank2 user1 = new newBank2();
        //user1.balance = 100; // not possible
        System.out.println(user1.deposit(100));
        user1.getBalance();

    }

}
