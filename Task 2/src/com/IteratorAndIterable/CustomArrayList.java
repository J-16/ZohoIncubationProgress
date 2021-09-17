package com.IteratorAndIterable;

import java.util.Iterator;

class ArrayList<E>{

    private int length = 10;
    private Object[] elements ;
    private int size = 0;

    private int iterate;


    public ArrayList(){
        elements = new Object[length];
    }

    public void add(E element){
        if(size<=length){
            elements[size++] = element;
        }
    }

    public Iterator iterator(){
        return new ArrayListIterator();
    }

    class ArrayListIterator implements Iterator{
        @Override
        public boolean hasNext() {
            return iterate!=size;
        }

        @Override
        public Object next() {
            return elements[iterate++];
        }
    }

}

public class CustomArrayList{
    public static void main(String[] args){

        ArrayList<String> arr = new ArrayList<>();
        arr.add("one");
        arr.add("two");
        arr.add("three");

        Iterator<String> iterator = arr.iterator();

        while(iterator.hasNext()){
            System.out.println( iterator.next() );
        }

    }
}
