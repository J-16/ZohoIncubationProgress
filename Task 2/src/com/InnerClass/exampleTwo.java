package com.InnerClass;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class User{

    class UserException{
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

        Set<String> set = new HashSet<String>();
        set.add("!");
        set.add("@");

        class ErrorCheck{

            public boolean ContainsSpecialCharacter(){

                for(int i=0;i<name.length();i++){
                    if( set.contains(name.charAt(i)) )
                        return true;
                }
                return false;
            }

            public boolean ContainsNumber(){
                return name.matches(".*[0-9].*");
            }

        }

        User user = new User();
        User.UserException userexception = user.new UserException();
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
    }
}
