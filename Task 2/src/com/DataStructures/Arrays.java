package com.DataStructures;

public class Arrays {

    private int length = 0;
    private int maxLength = 10;
    public int[] arr = new int[10];

    public void insert(int x){
        if(length > 10)
            return;
        arr[length++] = x;
    }

    public void insert(int index, int value){
        if(index > -1 && index < length) {
            for(int i = length; i>index; i--){
                arr[i] = arr[i-1];
            }
            arr[index] = value;
            length++;
        }
    }

    public int linearSearch(int arr[], int x){
        for(int i : arr){
            if(arr[i] == x)
                return i;
        }
        return -1;
    }

    public int binarySearch(int[] arr, int x){
        int l =0, r = length-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(arr[mid] == x) return mid;
            else if (x < arr[mid])
                r = mid-1;
            else
                l = mid+1;
        }
        return -1;
    }

    public int delete(int[] arr, int x){

        // assuming sorted
        int index  = binarySearch(arr,x);

        for(int i = index+1; i<length-1; i++){
            arr[i-1] = arr[i];
        }
        return --length;
    }

    public static void main(String[] args){
        Arrays array = new Arrays();

        //insertion
        System.out.println( "Binary Search before insertion: " +array.binarySearch(array.arr,2) );
        //At end - O(n)
        array.insert(1);
        array.insert(2);
        array.insert(3);
        array.insert(4);
        array.insert(5);
        array.insert(7);
        System.out.println( "Binary Search after insertion: " +array.binarySearch(array.arr,2) );

        // Insertion at any place other than end - O(n)
        array.insert(5,6);
        System.out.println( "Binary Search after insertion at index: " +array.binarySearch(array.arr,2) );

        // Access O(1)
        System.out.println("Access with index: " + array.arr[2]);

        // Search
        // linearSearch O (n) - sorted and unsorted
        System.out.println( "Linear Search: " + array.linearSearch(array.arr,2) );
        // binarySearch O (logn) - sorted
        System.out.println( "Binary Search : " + array.binarySearch(array.arr,2) );


        // delete O(n) - sorted and unsorted
        array.delete(array.arr,2);

        System.out.println( "Binary Search after deletion: " + array.binarySearch(array.arr,2) );

    }
}
