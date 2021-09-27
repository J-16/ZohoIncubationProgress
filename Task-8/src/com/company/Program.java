package com.company;

class Program{

    public static void addWords1(String str, String str1){
        StringBuilder result = new StringBuilder();
        int i =0;
        while( i<str.length() && i < str1.length() ){
            result.append( (char) ((( (str.charAt(i) - 97) + (str1.charAt(i++) - 97)  ) % 26 ) + 98) );
        }
        while(i<str.length()){
            result.append(str.charAt(i++));
        }
        while(i<str1.length()){
            result.append(str1.charAt(i++));
        }
        System.out.println(result);
    }

    public static void addWords(String str, String str1){
        StringBuilder result = new StringBuilder();
        int i =0;
        while( i<str.length() && i < str1.length() ){
            result.append( (char) ((((str.charAt(i) + str1.charAt(i)) - 194) % 26) + 98)  );
            i++;
        }
        System.out.println(result);
    }

    public static void main(String ...args){
        String str = "zoho";
        String str1 = "pogo";
        addWords(str, str1);
    }

}
