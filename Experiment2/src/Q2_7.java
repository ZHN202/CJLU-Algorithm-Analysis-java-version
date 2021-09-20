import java.util.*;

public class Q2_7 {
    public static void main(String[] args) {
        List arrayList =  Q2_5.createList(10,"arrayList");
        List linkedList = Q2_5.createList(10,"linkedList");
        ListIterator iter = arrayList.listIterator();
        System.out.println("old arraylist:");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        SwapArrayList(arrayList,4,5);
        iter = arrayList.listIterator();
        System.out.println("new arraylist:");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        iter = linkedList.listIterator();
        System.out.println("old linkedlist:");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        SwapLinkedList(linkedList,4,5);
        iter = linkedList.listIterator();
        System.out.println("new linkedlist:");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
    public static void SwapArrayList(List L,int index1, int index2){
        Object temp = L.get(index1);
        L.set(index1,L.get(index2));
        L.set(index2,temp);
    }
    public static void SwapLinkedList(List L,int index1,int index2){
        Object temp = L.get(index1);
        L.set(index1,L.get(index2));
        L.set(index2,temp);
    }

}

