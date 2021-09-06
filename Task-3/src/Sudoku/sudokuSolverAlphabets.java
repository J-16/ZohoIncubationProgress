package Sudoku;

import java.util.Scanner;

public class sudokuSolverAlphabets {
    private static boolean checkRow( char[][] matrix, int row, char val ){

        for(int i=0;i<matrix.length;i++){
            if( (matrix[row][i] != '0') && (matrix[row][i] ^ val) == 0 ){
                //System.out.println("present in row" + matrix[row][i] + " " +val);
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn( char[][] matrix, int col, char val ){

        for (char[] mat : matrix) {
            if ((mat[col] != '0') && (mat[col] ^ val) == 0) {
                //System.out.println(matrix[i][col]);
                return false;
            }
        }
        return true;
    }

    private static boolean checkBox(char[][] matrix, int row, int col, char val){

        int rowStart = row - row%3;
        int colStart = col - col%3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if( (matrix[i + rowStart][j+colStart] != 0) && ((matrix[i + rowStart][j+colStart] ^ val) == 0))
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        char[][] matrix = { { '0', 'G', '0', '0', 'C', '0', '0', 'D', 'F' }, // ROW
                { '0', 'F', '0', '0', '0', '0', 'H', 'I', '0' },
                { 'B', '0', '0', 'H', '0', '0', 'G', 'A', 'E' },  // C
                { '0', 'H', 'D', '0', 'I', 'G', '0', '0', '0' },  // O
                { 'G', 'A', '0', '0', '0', '0', '0', 'E', 'I' },  // L
                { '0', '0', '0', 'A', 'C', '0', 'D', 'H', '0' },
                { 'F', 'I', 'G', '0', '0', 'B', '0', '0', 'H' },
                { '0', 'E', 'H', '0', '0', '0', '0', 'F', '0' },
                { 'D', 'C', '0', '0', 'H', '0', '0', 'G', '0' } };

        Scanner sc = new Scanner(System.in);
        char value;

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
            System.out.println("Enter value to be entered between A to I at : " + "matrix[" + row + "][" + col +"]" +" or 0 to quit ");
            value = sc.next().charAt(0);

            if( matrix[row-1][col-1] == '0' ){
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