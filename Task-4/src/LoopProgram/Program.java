package LoopProgram;



public class Program {

    private static int parition(int arr[] ,int start, int end){
        int pivot = arr[end];
        int piv = start;
        for(int i=start; i<end; i++){
            if( arr[i] <= pivot ){
                int temp = arr[i];
                arr[i] = arr[piv];
                arr[piv++] = temp;
            }
        }
        int temp = arr[piv];
        arr[piv] = arr[end];
        arr[end] = temp;
        return piv;
    }

    private static void quickSort(int arr[], int start, int end){
        if(start < end){
            int pivot = parition(arr,start,end);
            quickSort(arr, start, pivot-1);
            quickSort(arr, pivot+1, end);
        }
    }

    private static void kthSmallest(int arr[], int k){
        System.out.println( k + " smallest element " + arr[k-1] );
        kthLargest(arr,k);
    }

    private static void kthLargest(int arr[], int k){
        System.out.println( k + " largest element " + arr[arr.length-k] );
    }

    public static void main(String[] args){
        int[] arr = {5,7,9,6,3,4,2,8,1};

        quickSort( arr,0, arr.length-1);

        kthSmallest(arr,2);
    }

}
