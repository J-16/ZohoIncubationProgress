package com.Strings.stringBuffer;

class reverse{
    public void rev(String S1, String S2){

        StringBuffer sb = new StringBuffer(S1+ S2);
        int i = 0 , j = sb.length()-1;

        while(i<j){
            String temp = sb.substring(i,i+1);
            sb.replace(i,i+1,sb.substring(j,j+1));
            sb.replace(j,j+1,temp);
            i++;
            j--;
        }
        System.out.println( sb );
    }

    public void rev1(String S1, String S2){
        StringBuffer sb = new StringBuffer(S1+S2);
        int i = 0, j = sb.length()-1;
        StringBuffer sb1 = new StringBuffer();
        while(j > -1){
            sb1.append(sb.charAt(j));
            j--;
        }
        System.out.println( sb1 );
    }

}


public class reverseString {

    public static void main(String[] s){
        String S1 = "esreveR";
        String S2 = "gnirtS";
        new reverse().rev(S1,S2);
        new reverse().rev1(S1,S2);
    }

}
