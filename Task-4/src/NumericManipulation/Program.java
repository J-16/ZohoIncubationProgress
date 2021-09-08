package NumericManipulation;

public class Program{

    private static void reverseNumber(int num){

        if(num < 0){
            System.out.println("No negative numbers allowed");
            return;
        }

        if(num > 0 && num < 10){
            System.out.println(num);
            return;
        }

        int result = 0;
        int temp = num;

        while( temp > 0 ){
            result += temp%10;
            result *= 10;
            temp = temp/10;
        }

        System.out.println( result/10 );
    }

    private static boolean check(int num){
        int temp = num;
        int last = temp %10;
        int prev = last;
        temp = temp/10;
        while(temp > 0){
            int current = temp%10;
            if(current != prev-1 && current != prev+1)
                return false;
            if(temp/10 == 0 && (current != last-1 && current != last+1)){
                return false;
            }
            temp = temp/10;
            prev = current;
        }
        return true;
    }

    private static void steppingNumber(int start, int end){

        int length = 0;
        if(start > end){
            System.out.println("start value must be smaller than end value");
            return;
        }
        for(int i=start; i<=end;i++){
            if(i<10){
                System.out.print(i + " ");
                length++;
            }
            else if(check(i)){
                System.out.print(i + " ");
                length++;
            }
        }
        if(length == 0){
            System.out.println("No stepping numbers");
        }
    }

    public static void main(String[] args){
        reverseNumber(1111);
        System.out.println();
        steppingNumber( 4000, 5000 );
        System.out.println();
        steppingNumber( 0, 21 );
        System.out.println();
        steppingNumber( 100, 150 );
    }

}
