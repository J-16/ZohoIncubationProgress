package com.Exceptions.UnChecked;

import java.util.ArrayList;
import java.util.List;

// RuntimeException to make it unchecked exception
class StringException extends RuntimeException{
    public StringException(String s){
        super(s);
    }
}

class User{

    public static String checkValidString(String name) throws StringException {

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

        ErrorCheck errorcheck = new ErrorCheck();

        if( name.length() == 0 ){
            throw new StringException("Empty Field is not allowed");
        }
        else if(  name.length() > 10 ){
            throw new StringException("More than 10 values not allowed");
        }
        else if ( errorcheck.ContainsSpecialCharacter() ) {
            throw new StringException("No special characters or symbols allowed");
        }
        else if( errorcheck.ContainsNumber() ){
            throw new StringException("Numbers are not allowed");
        }
        else
            return "ok";

    }
}

public class exampleOne {
    public static void main(String[] s){

        System.out.println(User.checkValidString("ajabcb"));

        System.out.println(User.checkValidString("a$jabcb"));

        System.out.println(User.checkValidString("aj1abcb"));

    }
}
