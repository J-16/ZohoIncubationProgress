package com;


public class WorkOut implements Cloneable{

    public static String toString(int i){
        int tempInt = i;
        int len = 0;
        while(tempInt > 0){
            tempInt = tempInt/10;
            len ++;
        }
        char[] character = new char[len];
        len = 0;
        while(i > 0){
            character[len] = (char) ((i%10) + '0');
            i = i /10;
            len++;
        }
        len--;
        int start = 0;
        while(start <= len){
            char temp = character[start];
            character[start++] = character[len];
            character[len--] = temp;
        }

        return new String(character);
    }

    public static void main(String[] args){
        int i = 100000;
        String s = toString(i);
        System.out.println(s);
    }
}
