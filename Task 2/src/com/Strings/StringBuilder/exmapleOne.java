package com.Strings.StringBuilder;

public class exmapleOne {

    public static void main(String[] args){
        StringBuilder sb = new StringBuilder("modnar");
        System.out.println(sb);
        System.out.println(sb.length());
        System.out.println(sb.reverse());
        sb.append(" ");
        sb.append("Strin");
        sb.insert(12,"g");
        System.out.println(sb);

        //char[] c = sb.toCharArray(); // not avail
    }
}
