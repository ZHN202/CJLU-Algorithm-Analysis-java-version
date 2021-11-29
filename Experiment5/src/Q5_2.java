import java.util.*;

public class Q5_2 {
    public static void main(String[] args) {
       // {4371, 1223, 6173, 4199, 4344, 9679, 1989
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(1,4371);
        hashMap.put(2,1223);
        hashMap.put(3,6173);
        hashMap.put(4,4199);
        hashMap.put(5,4344);
        hashMap.put(6,9679);
        hashMap.put(7,1989);
        hashMap.remove(1);
        hashMap.remove(4);
        System.out.print("HashMap:");
        for(int item:hashMap.values())
            System.out.print(item+"  ");
        System.out.print("\n");
        HashSet<Integer> hashSet=new HashSet<>();

        hashSet.add(4371);
        hashSet.add(1223);
        hashSet.add(6173);
        hashSet.add(4199);
        hashSet.add(4344);
        hashSet.add(9679);
        hashSet.add(1989);
        hashSet.remove(1223);
        hashSet.remove(4344);
        System.out.print("HashSet:");
        for(int item:hashSet)
            System.out.print(item+"  ");
        System.out.print("\n");
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(1,4371);
        treeMap.put(2,1223);
        treeMap.put(3,6173);
        treeMap.put(4,4199);
        treeMap.put(5,4344);
        treeMap.put(6,9679);
        treeMap.put(7,1989);
        treeMap.remove(1);
        treeMap.remove(4);
        System.out.print("HashMap:");
        for(int item:treeMap.values())
            System.out.print(item+"  ");
        System.out.print("\n");
        TreeSet<Integer> treeSet=new TreeSet<>();
        treeSet.add(4371);
        treeSet.add(1223);
        treeSet.add(6173);
        treeSet.add(4199);
        treeSet.add(4344);
        treeSet.add(9679);
        treeSet.add(1989);
        treeSet.remove(1223);
        treeSet.remove(4344);
        System.out.print("HashSet:");
        for(int item:treeSet)
            System.out.print(item+"  ");
        System.out.print("\n");

    }
}
