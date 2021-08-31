package com.Strings;

class customString{
    private final char[] c;
    private int length =0;

    customString(char[] c){
        this.c = c;
        this.length = c.length;
    }

    public int length(){
        return length;
    }

    public char charAt(int index){
        if(index > 0 && index < length)
            return c[index];
        throw new ArrayIndexOutOfBoundsException(index);
    }
}

public class customStringImpl {
    public static void main(String[] s){
        char[] c = {'s','t','r'};
        customString str = new customString(c);
        System.out.println(str.length());
        System.out.println(str.charAt(2));
    }

}
