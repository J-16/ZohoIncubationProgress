package com.lambdas.methodReference.constructor;

import java.util.Scanner;

interface CharacterInterface{
    CharactersBody SelectCharacter(String Body, int blood, String name);
}

class CharactersBody {
    
    private int Blood;
    private String playerName;
    private String Body;

    public CharactersBody(String Body, int blood, String name){
        this.Body = Body;
        this.Blood = blood;
        playerName = name;
        System.out.println("Created Character");
    }

    public int Hit(){
        System.out.println("Beat");
        return 10;
    }

    public void looseBlood(int hit){
        this.Blood -= hit;
    }

    public int getBlood(){
        return Blood;
    }

    public String toString(){
        return "Body : " + Body + " name: " + playerName + " Blood: " + Blood;
    }

}

public class exampleThree {

    public static void main(String[] args){
        CharacterInterface characterInterface = null;
        CharactersBody charactersBody = null;

        Scanner scanner = new Scanner(System.in);
        int c;

        do{
            System.out.println("Select 1.Hulk or 2.IronMan");
            c = scanner.nextInt();
            switch (c){
                case 1 :
                    characterInterface = CharactersBody::new;
                    charactersBody = characterInterface.SelectCharacter("HulkBody",100,"user1");
                    break;
                case 2:
                    characterInterface = CharactersBody::new;
                    charactersBody = characterInterface.SelectCharacter("IronManBody",80,"user1");
                    break;
                default:
                    System.out.println("Only two Characters avail");
            }
        }while( c!=1 && c!=2 );

        System.out.println("Game Started");

        do{
            System.out.println("Selection Action : 1.Hit 2. Settings 3. quit");
            c = scanner.nextInt();
            switch (c){
                case 1 :
                    charactersBody.Hit();
                    break;
                case 2 :
                    System.out.println( charactersBody);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }while( charactersBody.getBlood() > 0 );

    }

}
