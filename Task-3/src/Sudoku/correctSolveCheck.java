package Sudoku;

public class correctSolveCheck {

    private static boolean checkRow( int[][] matrix, int row,int col, int val ){

        for(int i=col+1;i<matrix.length;i++){
            if( (matrix[row][i] != 0) && (matrix[row][i] ^ val) == 0 ){
                System.out.println( "matrix[" + (row+1) + "][" + (i+1) + "] " );
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn( int[][] matrix, int row, int col, int val ){

        for(int i=row+1;i<matrix.length;i++) {
            if ((matrix[i][col] != 0) && (matrix[i][col] ^ val) == 0) {
                System.out.println( "matrix[" + (row+1) + "][" + (i+1) + "] ");
                return false;
            }
        }
        return true;
    }

    private static boolean checkBox(int[][] matrix, int row, int col, int val){

        int rowStart = row - row%3;
        int colStart = col - col%3;
        for(int i=col+1;i<3;i++){
            for(int j=row+1;j<3;j++){
                if( (matrix[i + rowStart][j+colStart] != 0) && ((matrix[i + rowStart][j+colStart] ^ val) == 0)){
                    System.out.println( "matrix[" + (row+1) + "][" + (i+1) + "] " );
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkSolve(int[][] matrix){

        for(int i=0;i< matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if( matrix[i][j] == 0){
                    System.out.println("please fill the values to check");
                    return;
                }
                if( !checkRow(matrix, i, j, matrix[i][j]) ||
                        !checkColumn(matrix, i, j,matrix[i][j]) ||
                        !checkBox(matrix, i, j ,matrix[i][j] ) ) {
                    System.out.println("incorrect");
                    return;
                }
            }
        }

        System.out.println("Correct Solve");

    }
}
