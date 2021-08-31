package com.DataStructures;

class customLinkedList{
    private int data;
    private customLinkedList next;

    private customLinkedList root = null;

    public customLinkedList(){}

    private customLinkedList(int data){
        this.data = data;
        next=null;
    }

    public void insert(int value){

        if(root == null){
            root = new customLinkedList(value);
            return;
        }

        customLinkedList temp = root;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new customLinkedList(value);
    }

    public void insert(int position, int value){

        if(root == null){
            root = new customLinkedList(value);
            return;
        }

        customLinkedList temp = root;
        int count=1;
        while(temp.next != null && count < position-1){
            temp = temp.next;
            count++;
        }
        customLinkedList prev = temp;

        if(temp.next != null){
            if(temp.next.next != null){
                temp = temp.next;
                customLinkedList current = new customLinkedList(value);
                current.next = temp;
                prev.next = current;
                return;
            }
        }
        temp.next = new customLinkedList(value);

    }

    public int search(int x){
        customLinkedList temp = root;
        while(temp != null){
            if(x == temp.data)
                return 1;
            temp = temp.next;
        }
        return -1;
    }

    public void delete(int value){
        if(root == null){
            return;
        }
        customLinkedList temp = root;
        customLinkedList prev = root;

        while(temp != null){
            if(value == temp.data){
                prev.next = temp.next;
                return;
            }
            prev= temp;
            temp = temp.next;
        }
    }

    public void deleteAt(int position){
        if(root == null){
            return;
        }

        customLinkedList temp = root;
        customLinkedList prev = root;

        int count=1;
        while(temp.next != null ){
            if(count == position){
                prev.next = temp.next;
            }
            prev = temp;
            temp = temp.next;
            count++;
        }
    }

    public void print(){
        customLinkedList temp = root;
        while(temp != null){
            System.out.print( temp.data + " ");
            temp = temp.next;
        }
    }
}


public class LinkedLists {
    public static void main(String[] args){
        customLinkedList linkedList = new customLinkedList();

        //insert
        // at end - O(1)
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.insert(5);
        linkedList.insert(6);
        System.out.println("After insertion at end");
        linkedList.print();
        System.out.println();

        // at another place - O(1)
        linkedList.insert(4,4);
        linkedList.insert(8,7);
        System.out.println("After insertion at nth place");
        linkedList.print();
        System.out.println();

        //search O(n)
        System.out.println( "Linear Search: " + linkedList.search(1) );

        // Deletion- O(1)
        System.out.println("After deleting a value");
        linkedList.delete(2);
        linkedList.print();
        System.out.println();

        System.out.println("After deletion at nth position");
        linkedList.deleteAt(3);
        linkedList.deleteAt(4);
        linkedList.print();
        System.out.println();

    }
}
