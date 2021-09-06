package ArrayManipulation;

import java.util.Arrays;

public class Program {

    static int start = 0, end = 0;
    private static void findLargestSubArray(int[] arr){
        int countSoFar = -1;
        for(int i=0;i<arr.length;i++){
            int count0 = 0;
            int count1 = 0;
            if(arr[i] == 0) count0++;
            else count1++;
            int j=i+1;
            for(;j<arr.length;j++){
                if(arr[j] == 0) count0++;
                else count1++;
                int countSum = count0 + count1;
                if( (countSoFar < countSum ) && count0 == count1 ){
                    start = i;
                    end = j;
                    countSoFar = countSum;
                }
            }
        }
        if( countSoFar == -1){
            System.out.println("No sub array found");
        }
        else {
            System.out.println(start + " to " + end);
        }
    }

    private static void nextGreaterNumber(int[] arr){
        for(int i = 0; i<arr.length; i++){
            int maxSorFar = Integer.MAX_VALUE;
            for(int j=i+1; j<arr.length; j++){
                if( arr[i] < arr[j] && maxSorFar > arr[j] ){
                    maxSorFar = arr[j];
                }
            }
            if(maxSorFar == Integer.MAX_VALUE) arr[i] = -1;
            else arr[i] = maxSorFar;
        }
        for(int i : arr)
            System.out.print(i + " ");
    }

    private static void nextGreaterNumber2(int[] arr){
        for(int i = 0; i<arr.length; i++){
            int[] result = arr.clone();
            Arrays.sort(result, i+1, result.length);
            if( i+1 < arr.length && arr[i] < result[i+1] ) arr[i] = result[i+1];
            else arr[i] = -1;
        }
        for(int i : arr)
            System.out.print(i + " ");
    }

    private static void arrangeArray(char[] arr){
        char firstOcc = ' ';
        char secondOcc = ' ';
        char thirdOcc = ' ';

        for (char c : arr) {
            if (firstOcc == ' ')
                firstOcc = c;
            else if (c != firstOcc && secondOcc == ' ')
                secondOcc = c;
            else if( c!= firstOcc && c!= secondOcc){
                thirdOcc = c;
                break;
            }
        }

        int[] count = new int[26];

        for(char c : arr){
            count[ c - 65 ]++;
        }

        int i=0;
        while(count[firstOcc - 65] > 0){
            arr[i++] = firstOcc;
            count[firstOcc - 65]--;
        }

        while(count[secondOcc - 65] > 0){
            arr[i++] = secondOcc;
            count[secondOcc - 65]--;
        }

        while(count[thirdOcc - 65] > 0){
            arr[i++] = thirdOcc;
            count[thirdOcc - 65]--;
        }

        for(char c : arr)
            System.out.print(  c + " " );

    }

    public static void main(String[] args){
        int[] arr  = {1,0,1,1,1,0,0,0};
        findLargestSubArray(arr);

        System.out.println();
        int[] arr2 = {2, 4, 8, 90, 77, 54};
        nextGreaterNumber(arr2);

        System.out.println();
        int[] arr3 = {2, 4, 8, 90, 77, 54};
        nextGreaterNumber2(arr3);

        System.out.println();
        System.out.println();
        char[] carr = {'B', 'G', 'B', 'R', 'G', 'R', 'B', 'G', 'R', 'R', 'B'};
        arrangeArray(carr);

    }
}
