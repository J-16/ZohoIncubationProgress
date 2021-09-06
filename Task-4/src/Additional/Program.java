package Additional;

public class Program {

    private static int digitSum(int num){
        int sum = 0;
        int temp = num;
        while(temp>0){
            sum += temp%10;
            temp = temp/10;
        }
        return sum;
    }

    private static boolean isMultiple(int x, int y){
        if(x%y == 0) return true;
        return y % x == 0;
    }

    static int prev = 0;
    static int val = 0;
    private static boolean traps(int x, int y){
        if(prev == x)
            return false;
        if( x == y || isMultiple(x,y) ) {
            System.out.print( val + " ");
            return true;
        }
        else{
            prev = x;
            int z = digitSum(x);
            return traps(z, y);
        }
    }

    private static boolean generate(int n1, int n2, int y){
        for(int x=n1; x<=n2; x++ ){
            val = x;
            if( traps(x,y) ){
                y+=2;
            }
            else y--;
            if(y < 3) y=3;
        }
        return false;
    }

    public static void main(String[] args){
        generate(2,8,3);

        System.out.println();
        generate(15,45,50);

        System.out.println();
        generate(25,20,5);
    }

}
