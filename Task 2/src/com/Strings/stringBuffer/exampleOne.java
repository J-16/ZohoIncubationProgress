package com.Strings.stringBuffer;

public class exampleOne {
    public static void main(String[] s){
        StringBuffer sb = new StringBuffer("rndom");
        sb.append("S");
        sb.append("t");
        sb.append("r");
        sb.append("i");
        sb.append("n");
        sb.append("g");

        System.out.println( sb );

        System.out.println( sb.capacity() ); // reserved space
        System.out.println( sb.length() ); // length

        sb.insert(1,'a');
        System.out.println( sb );

        sb.reverse();
        System.out.println(sb);
        sb.delete(6,13);
        sb.reverse();
        System.out.println(sb);
        sb.append("i");
        sb.deleteCharAt(6);
        System.out.println(sb);

        sb.replace(0,1,"s");
        System.out.println( sb );

        System.out.println(sb.charAt(1));

        System.out.println( sb.substring(0,2) );
        sb.trimToSize();
        System.out.println( sb.capacity() );

    }
}
