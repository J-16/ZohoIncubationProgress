package com.ATM.DataBase;

import com.ATM.Exceptions.UserException;
import com.ATM.Model.Account;

import java.io.*;
import java.util.ArrayList;

public class BankDatabase{
    //private static HashMap<Long, Account> userDb = new HashMap<>();

    private static final String FILE = "userDb.txt";

    public static void display(){
        try {
            FileInputStream fileReader = new FileInputStream(FILE);
            ObjectInputStream object = null;
            while (true) {
                try {
                    object = new ObjectInputStream(fileReader);
                    Object account = object.readObject();
                    if (account != null)
                        System.out.println("acc " + account);
                    else break;
                } catch (Exception e) {
                    break;
                }
            }
            if(object != null)
                object.close();
        }
        catch (IOException e){
            System.out.println();
        }
    }

    private static ArrayList<Account> readFromFile(Account updatedAccount) throws FileNotFoundException {
        ArrayList<Account> accountsList = new ArrayList<>();
        FileInputStream fileReader = new FileInputStream(FILE);
        while( true ){
            try{
                ObjectInputStream object = new ObjectInputStream(fileReader);
                Account account = (Account) object.readObject();
                if(account.getCustomerInfo().getATMNumber() != updatedAccount.getCustomerInfo().getATMNumber())
                    accountsList.add(account);
            }catch(Exception e){
                return accountsList;
            }
        }
    }

    public static void updateCustomer(Account updatedAccount) throws IOException, ClassNotFoundException {

        ArrayList<Account> accountsList = readFromFile(updatedAccount);

        FileInputStream fileReader = new FileInputStream(FILE);
        while( true ){
            ObjectInputStream object = new ObjectInputStream(fileReader);
            Account account = (Account) object.readObject();
            if(account.getCustomerInfo().getATMNumber() == updatedAccount.getCustomerInfo().getATMNumber()){
                FileOutputStream fileWriter = new FileOutputStream(FILE);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileWriter);
                objectOutputStream.writeObject( updatedAccount );
                objectOutputStream.flush();
                int i = 0;
                while(i < accountsList.size()){
                    objectOutputStream = new ObjectOutputStream(fileWriter);
                    objectOutputStream.writeObject( accountsList.get(i++) );
                }
                return;
            }
        }
    }

    public static Account getCustomer(long ATMCardNo) throws IOException {
        //display();
        FileInputStream fileReader = new FileInputStream(FILE);
        while( true ){
            try{
                ObjectInputStream object = new ObjectInputStream(fileReader);
                Account account = (Account) object.readObject();
                if(account.getCustomerInfo().getATMNumber() == ATMCardNo)
                    return account;
            }catch(Exception e){
                throw new UserException("Invalid Card");
            }
        }
    }

    public static void registerCustomer(Account account) throws IOException {
        FileOutputStream fileWriter = new FileOutputStream(FILE, true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileWriter);
        objectOutputStream.writeObject( account );
    }


//    private static FileWriter fileWriter;
//    private static FileReader fileReader;
//
//    public static boolean checkValidCard(long ATMCardNo) throws IOException {
//        int c = fileReader.read();
//        while( c != -1 ){
//            int x = 0;
//            while( c != ' ' && c != -1){
//                x += c - '0';
//                c = fileReader.read();
//                x*=10;
//            }
//            x = x/10;
//            if(ATMCardNo == x)
//                return true;
//            while(c != '\n' && c != -1){
//                c = fileReader.read();
//            }
//            c = fileReader.read();
//        }
//        return  false;
//        //return BankDatabase.userDb.containsKey(ATMCardNo);
//    }
//
//    public static Account getCustomer(long ATMCardNo) throws IOException {
//        if( !checkValidCard(ATMCardNo) )
//            throw new UserException("Invalid Card Number");
//        Account account = null;
//        int c = fileReader.read();
//        while( c != -1 ){
//            int x = 0;
//            while( c != ' ' && c != -1){
//                x += c - '0';
//                c = fileReader.read();
//                x*=10;
//            }
//            x = x/10;
//            if(ATMCardNo == x){
//                StringBuilder name = new StringBuilder();
//                String str = null;
//                int pin = 0;
//                double balance = 0;
//                while( c != '\n'){
//                    while(c != ' '){
//                        c = fileReader.read();
//                        if(str == null)
//                    }
//                    str = name.toString();
//                    c = fileReader.read();
//                }
//            }
//            while(c != '\n' && c != -1){
//                c = fileReader.read();
//            }
//            c = fileReader.read();
//        }
//        return account;
//    }

}