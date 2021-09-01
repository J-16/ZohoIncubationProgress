package com.InnerClass;

import java.util.ArrayList;
import java.util.List;

class User{

    class StringException{
        public String emptyException(){
            return "Empty Field is not allowed";
        }
        public String highException(){
            return "More than 10 values not allowed";
        }
        public String specialSymbolException(){
            return "No special characters or symbols allowed";
        }
        public String numberException(){
            return "Numbers are not allowed";
        }
    }

    public static String checkValidString(String name) {

        List<Character> symbols = new ArrayList<>();
        symbols.add('!');
        symbols.add('@');
        symbols.add('#');
        symbols.add('$');
        symbols.add('%');
        symbols.add('^');
        symbols.add('&');
        symbols.add('*');

        class ErrorCheck{

            public boolean ContainsSpecialCharacter(){

                for(int i=0;i<name.length();i++){
                    if( symbols.contains(name.charAt(i)) )
                        return true;
                }
                return false;
            }

            public boolean ContainsNumber(){
                return name.matches(".*[0-9].*");
            }

        }

        User user = new User();
        User.StringException userexception = user.new StringException();
        ErrorCheck errorcheck = new ErrorCheck();

        if( name.length() == 0 ){
            return userexception.emptyException();
        }
        else if( name.length() > 10){
            return userexception.highException();
        }
        else if ( errorcheck.ContainsSpecialCharacter() ) {
            return userexception.specialSymbolException();
        }
        else if( errorcheck.ContainsNumber() ){
            return userexception.numberException();
        }
        else
            return "ok";

    }
}

public class exampleTwo {
    public static void main(String[] s){
        System.out.println(User.checkValidString("ajabcb"));

        System.out.println(User.checkValidString("a$jabcb"));
        System.out.println(User.checkValidString("aj1abcb"));
    }
}
