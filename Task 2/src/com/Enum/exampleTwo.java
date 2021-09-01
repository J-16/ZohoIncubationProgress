package com.Enum;


import java.util.Scanner;

enum Keys{
    UP(38),DOWN(40),RIGHT(39),LEFT(37);

    private int keyCode;

    Keys(int keyCode) {
        this.keyCode = keyCode;
    }
    public int getKeyCode(){
        return this.keyCode;
    }
}

class Game{
    public void spin(){
        Keys keys[] = Keys.values();
    }
}

public class exampleTwo {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char c;
        do{
            System.out.println( "Select Player : \n " );
            c = sc.next().charAt(0);

        }while(c != 'q');
    }

}
