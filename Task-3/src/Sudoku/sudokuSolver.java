package Sudoku;

import java.util.Scanner;

public class sudokuSolver {



    static int[][] matrixSolved = {
            { 8, 7, 5, 9, 2, 1, 3, 4, 6 }, // ROW
            { 3, 6, 1, 7, 5, 4, 8, 9, 2 },
            { 2, 4, 9, 8, 6, 3, 7, 1, 5 },  // C
            { 5, 8, 4, 6, 9, 7, 1, 2, 3 },  // O
            { 7, 1, 3, 2, 4, 8, 6, 5, 9 },  // L
            { 9, 2, 6, 1, 3, 5, 4, 8, 7 },
            { 6, 9, 7, 4, 1, 2, 5, 3, 8 },
            { 1, 5, 8, 3, 7, 9, 2, 6, 4 },
            { 4, 3, 2, 5, 8, 6, 9, 7, 1 } };


    static int[][] matrix = {
            { 0, 7, 0, 0, 2, 0, 0, 4, 6 }, // ROW
            { 0, 6, 0, 0, 0, 0, 8, 9, 0 },
            { 2, 0, 0, 8, 0, 0, 7, 1, 5 },  // C
            { 0, 8, 4, 0, 9, 7, 0, 0, 0 },  // O
            { 7, 1, 0, 0, 0, 0, 0, 5, 9 },  // L
            { 0, 0, 0, 1, 3, 0, 4, 8, 0 },
            { 6, 9, 7, 0, 0, 2, 0, 0, 8 },
            { 0, 5, 8, 0, 0, 0, 0, 6, 0 },
            { 4, 3, 0, 0, 8, 0, 0, 7, 0 } };

    private static Scanner sc = new Scanner(System.in);

    private static boolean checkRow( int row, int val ){

        for(int i=0;i<matrix.length;i++){
            if( (matrix[row][i] != 0) && (matrix[row][i] ^ val) == 0 ){
                System.out.println( "matrix[" + (row+1) + "][" + (i+1) + "] " );
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn( int col, int val ){

        for(int i=0;i<matrix.length;i++) {
            if ((matrix[i][col] != 0) && (matrix[i][col] ^ val) == 0) {
                System.out.println( "matrix[" + (i+1) + "][" + (col+1) + "] ");
                return false;
            }
        }
        return true;
    }

    private static boolean checkBox( int row, int col, int val){

        int rowStart = row - row%3;
        int colStart = col - col%3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if( (matrix[i + rowStart][j+colStart] != 0) && ((matrix[i + rowStart][j+colStart] ^ val) == 0)){
                    System.out.println( "matrix[" + (j+1) + "][" + (i+1) + "] " );
                    return false;
                }
            }
        }
        return true;
    }


    private static void solve(){
        int value;
        int row;
        int col;
        do{
            System.out.println("0 to go back");
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length; j++){
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Enter row number : ");
            row = sc.nextInt();
            System.out.println("Enter column number : ");
            col = sc.nextInt();
            System.out.println("Enter value to be entered between 1 to 9 at : " + "matrix[" + row + "][" + col +"]" +" or 0 to quit ");
            value = sc.nextInt();

            if(value == 0 || col == 0 || row == 0)
                return;

            if( matrix[row-1][col-1] == 0 ){
                if( checkRow(row-1, value) && checkColumn(col-1, value) &&
                        checkBox(row-1,col-1,value) )
                    matrix[row-1][col-1] = value;
                else
                    System.out.println( value + " is already present ");
            }
            else
                System.out.println( "Given row and col is not empty, please choose a empty value ");


        }while(true);
    }

    private static void getValue(){
        int value;
        do{
            System.out.println("Enter value : ");
            value = sc.nextInt();
            if(value > 0 && value < 9){
                for(int i=0;i< matrix.length;i++){
                    for(int j=0;j<matrix[i].length;j++){
                        if(checkRow(i,value)){
                            if(checkColumn(j,value)){
                                if( checkBox(i, j, value) )
                                    System.out.println("not found");
                            }
                            else break;
                        }
                        else break;
                    }
                }
            }

        }while(value !=0);
    }


    public static void main(String[] args) {

        int value;

        do{
            System.out.println(" 1.Solve 2.Check if correct solve 3.get value 0.quit ");
            value = sc.nextInt();

            switch(value){
                case 0:
                    return;
                case 1 :
                    solve();
                    break;
                case 2:
                    correctSolveCheck.checkSolve(matrix);
                    break;
                case 3:
                    getValue();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }while(true);



    }
}
