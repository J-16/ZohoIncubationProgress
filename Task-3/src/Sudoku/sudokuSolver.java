package Sudoku;

import java.util.Scanner;

public class sudokuSolver {
    private static boolean checkRow( int[][] matrix, int row, int val ){

        for(int i=0;i<matrix.length;i++){
            if( (matrix[row][i] != 0) && (matrix[row][i] ^ val) == val ){
                System.out.println("present in row" + matrix[row][i] + " " +val);
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn( int[][] matrix, int col, int val ){

        for(int i=0;i<matrix.length;i++){
            if( (matrix[i][col] != 0) && (~(matrix[i][col]) & val) == 0 )
                return false;
        }

        return true;
    }

    private static boolean checkBox(int[][] matrix, int row, int col, int val){

        int rowStart = row - row%3;
        int colStart = col - col%3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if( (matrix[i + rowStart][j+colStart] != 0) && ((matrix[i + rowStart][j+colStart] ^ val) == val))
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        int[][] matrix = { { 0, 7, 0, 0, 2, 0, 0, 4, 6 }, // ROW
                           { 0, 6, 0, 0, 0, 0, 8, 9, 0 },
                           { 2, 0, 0, 8, 0, 0, 7, 1, 5 },  // C
                           { 0, 8, 4, 0, 9, 7, 0, 0, 0 },  // O
                           { 7, 1, 0, 0, 0, 0, 0, 5, 9 },  // L
                           { 0, 0, 0, 1, 3, 0, 4, 8, 0 },
                           { 6, 9, 7, 0, 0, 2, 0, 0, 8 },
                           { 0, 5, 8, 0, 0, 0, 0, 6, 0 },
                           { 4, 3, 0, 0, 8, 0, 0, 7, 0 } };

        Scanner sc = new Scanner(System.in);
        int value;

        do{
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length; j++){
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Enter row number : ");
            int row = sc.nextInt();
            System.out.println("Enter column number : ");
            int col = sc.nextInt();
            System.out.println("Enter value to be entered between 1 to 9 at : " + "matrix[" + row + "][" + col +"]" +" or 0 to quit ");
            value = sc.nextInt();

            if( matrix[row-1][col-1] == 0 ){
                if( checkRow(matrix, row-1, value) && checkColumn(matrix, col-1, value) && checkBox(matrix,row-1,col-1
                        ,value) )
                    matrix[row-1][col-1] = value;
                else
                    System.out.println( value + " is already present ");
            }
            else
                System.out.println( "Given row and col is not empty, please choose a empty value ");


        }while(value != 0);

    }
}
