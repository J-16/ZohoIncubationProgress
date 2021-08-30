package com.Strings;

class stringOperation{

    String str = "random";
    String str1 = "String";

    public String toString(){
        return str + " " + str1;
    }
}
class stringBuilderOperation{
    String str = "random";
    String str1 = "String";

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str1);
        return sb.toString();
    }
}


public class performanceIssue {
    public static void main(String[] args){
        // Both the functions below doesn't make any difference
        System.out.println( new stringOperation()  + " " +  new stringBuilderOperation() );

        // this works on the same object again and again
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10;i++)
            sb.append("*");

        //This is costly as it will create a new object everytime
        String s = "";
        for(int i=0;i<10;i++)
            s += "*";

        System.out.println( sb + " " + s );

        //TODO while : learning multi threading
        // StringBuffer (Thread safety is achieved)

    }
}
