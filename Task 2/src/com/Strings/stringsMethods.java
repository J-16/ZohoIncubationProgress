package com.Strings;

public class stringsMethods {
    public static void main(String[] s){

        String str = "random";

        System.out.println( str.length() );

        System.out.println( str.charAt(5) );

        System.out.println( str.substring(2) ); // startIndex to end
        System.out.println( str.substring(1,5) ); //startIndex to endIndex-1 (5-1)

        String str1 = "words";
        String str2 = str.concat(str1); // new object is created and assigned to str2;
        System.out.println( str2 );

        System.out.println( str2.indexOf('o') );
        System.out.println( str2.indexOf('o',5) );
        System.out.println( str2.lastIndexOf('o') ); //starts from last

        String str3 = "random";
        System.out.println( str.equals(str3) );

        String str4 = "RanDOm";
        System.out.println( str.equalsIgnoreCase(str4) );

        System.out.println( str3.compareTo(str) );
        System.out.println( str.compareTo(str4) ); // random RanDom r-R
        System.out.println( str.compareToIgnoreCase(str4) );
        str4 = str4.toLowerCase();
        System.out.println( str4  );
        System.out.println( str4.toUpperCase() );

        String str5 = " R A N D O M ";
        System.out.println( str5.trim() ); //removes whitespace at start and end only

        str4 = str4.replace('R','r');
        System.out.println(str4);
        str5 = "RandomRandom";
        System.out.println( str5.replaceAll("R","r") );

        //more

        System.out.println( str.codePointAt(1) );
        System.out.println( str.codePointBefore(2) );
        String str6 = "random123456!@#";
        System.out.println( str6.codePointCount(0,str6.length()) ); // number of characters

        String str7 = "some random string";
        String str8 = (String) str.subSequence(0,3); // similar to substring but return CharSequence

        System.out.println( str7.contains(str8) );
        System.out.println( str7.contains("randO") );
        System.out.println( str7.contentEquals(str8) ); // exact match only
        System.out.println( str7.contentEquals("some random string") );

        char[] c = new char[6];
        str7.getChars(5,11,c,0);
        System.out.println(c); // copies within given range

        char[] c1 = str7.toCharArray();
        System.out.println(c1); // copies entire string

        System.out.println(str7.hashCode()); // memoryAddress + someHashFunctionCode (Java doesn't provide address of anaything directly)\

        System.out.println(" --- ");
        String str9 = "new Strings";
        String str10 = "new Strings";
        System.out.println( str9.hashCode() + " " + str10.hashCode() );
        System.out.println( str9 == str10 );
        String str11 = new String("new Strings"); // created in heap and reference is in string pool
        System.out.println( str11.hashCode() );
        System.out.println( str9 == str11 );
        String strCopy9 = str11;
        System.out.println( str11 == strCopy9 );
        System.out.println( str9 == strCopy9 );

        strCopy9 = new String(str9);
        System.out.println( str11 == strCopy9 );
        System.out.println( str9 == strCopy9 );

        strCopy9 = str9;
        System.out.println( str11 == strCopy9 );
        System.out.println( str9 == strCopy9 );

        System.out.println(" --- ");
        String str12 = "";
        System.out.println( str12.isEmpty() + " " + str11.isEmpty() );

        String[] strArray = str7.split("\\s");
        for(String i : strArray)
            System.out.println(i);

        String str14 = "some random string has been added for test";
        String[] strArray1 = str14.split("\\s",3); // with limiting array length
        for(String i : strArray1)
            System.out.println(i);

        System.out.println( String.join("-","Hello" , "Im" , "Good") );
        // System.out.println( String.join("-",str14) );

    }
}
