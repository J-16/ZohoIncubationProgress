package com.datahiding;

interface bankMethod{
    int withdraw(int amount);
    int deposit(int amount);
}

class Bank implements bankMethod{

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

public class exampleOne {

    public static void main(String[] s){

        bankMethod user1 = new Bank();
        //user1.balance = 100; // not possible
        System.out.println(user1.deposit(100));
        //user1.getBalance(); // not possible

    }

}
