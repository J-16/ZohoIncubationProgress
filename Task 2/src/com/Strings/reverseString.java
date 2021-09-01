package com.Strings;

public class reverseString {
    public static void main(String[] s){
        String S1 = "esreveR";
        String S2 = "gnirtS";

        String str = S1.concat(S2);
        char[] str1 = str.toCharArray();

        int i = 0, j = str.length()-1;
        while(i<j){
            char temp = str1[i];
            str1[i] = str1[j];
            str1[j] = temp;
            i++;
            j--;
        }
        str =  new String(str1);
        System.out.println( str );

    }
}
