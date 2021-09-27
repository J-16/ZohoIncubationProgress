package com.company;

import java.io.*;

public class Main {


    public static void main(String[] args) {

        String filename = "temp.txt";
        File file = new File(filename);
        file.setWritable(false);

        try{
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("Hello");
            fileWriter.flush();
            fileWriter.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        //READ
        try{
            FileReader fileReader = new FileReader(file);
            int i = 0;
            while( ( i = fileReader.read()) != -1){
                System.out.print((char)i);
            }
            fileReader.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int i=0;
            while( ( i = bufferedReader.read()) != -1 )
                System.out.print( (char) i);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
