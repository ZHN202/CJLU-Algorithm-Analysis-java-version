import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q5_1 {
    public static void main(String[] args) {
        //分离链接散列表
        SeparateChainingHashTable separateChainingHashTable = new SeparateChainingHashTable();
        separateChainingHashTable.insert(4371);
        separateChainingHashTable.insert(1223);
        separateChainingHashTable.insert(6173);
        separateChainingHashTable.insert(4199);
        separateChainingHashTable.insert(4344);
        separateChainingHashTable.insert(9679);
        separateChainingHashTable.insert(1989);

        QuadraticProbingHashTable quadraticProbingHashTable1=new QuadraticProbingHashTable();
        quadraticProbingHashTable1.setFuncMode(1);
        quadraticProbingHashTable1.insert(4371);
        quadraticProbingHashTable1.insert(1223);
        quadraticProbingHashTable1.insert(6173);
        quadraticProbingHashTable1.insert(4199);
       quadraticProbingHashTable1.insert(4344);
        quadraticProbingHashTable1.insert(9679);
        quadraticProbingHashTable1.insert(1989);

        QuadraticProbingHashTable quadraticProbingHashTable2=new QuadraticProbingHashTable();
        quadraticProbingHashTable2.setFuncMode(2);
        quadraticProbingHashTable2.insert(4371);
        quadraticProbingHashTable2.insert(1223);
        quadraticProbingHashTable2.insert(6173);
        quadraticProbingHashTable2.insert(4199);
        quadraticProbingHashTable2.insert(4344);
        quadraticProbingHashTable2.insert(9679);
        quadraticProbingHashTable2.insert(1989);

        QuadraticProbingHashTable quadraticProbingHashTable3=new QuadraticProbingHashTable();
        quadraticProbingHashTable3.setFuncMode(3);
        quadraticProbingHashTable3.insert(4371);
        quadraticProbingHashTable3.insert(1223);
        quadraticProbingHashTable3.insert(6173);
        quadraticProbingHashTable3.insert(4199);
        quadraticProbingHashTable3.insert(4344);
        quadraticProbingHashTable3.insert(9679);
        quadraticProbingHashTable3.insert(1989);
    }
}

class SeparateChainingHashTable {
    //公有成员
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<>();
    }

    public void insert(int data) {
        List<Integer> list = theLists[myhash(data)];
        if (!list.contains(data)) {
            list.add(data);
            if (++currentSize > theLists.length)
                rehash();
        }
    }

    public void remove(int data) {
        List<Integer> list = theLists[myhash(data)];
        if (list.contains(data)) {
            list.remove(data);
            currentSize--;
        }
    }

    public boolean contain(int data) {
        List<Integer> list = theLists[myhash(data)];
        return list.contains(data);
    }

    public void makeEmpty() {
        for (LinkedList theList : theLists) theList.clear();
        currentSize = 0;
    }

    //私有成员
    private static final int DEFAULT_TABLE_SIZE = 101;

    private LinkedList[] theLists;

    private int currentSize;

    private void rehash() {
        List<Integer>[] oldLists = theLists;
        theLists = new LinkedList[nextPrime(2 * theLists.length)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<>();
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++)
            for (int item : oldLists[i])
                insert(item);
    }

    private int hash(int data) {
        return data % 11;
    }

    private int myhash(int data) {
        int hashVal = hash(data);
        hashVal %= theLists.length;
        if (hashVal < 0)
            hashVal += theLists.length;
        return hashVal;
    }

    private static int nextPrime(int n) {
        if (isPrime(n)) return n;
        else return (n + 1);
    }

    private static boolean isPrime(int n) {
        return n % 2 == 1;
    }
}

class QuadraticProbingHashTable {
    //公有成员
    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);

    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public void setFuncMode(int mode){
        funcMode=mode;
    }
    public void insert(int data) {
        int currentPos = findPos(data);
        if (isActive(currentPos))
            return;
        array[currentPos] = new HashEntry(data, true);
        if (currentSize > array.length / 2)
            rehash();
    }

    public void remove(int data) {
        int currentPos = findPos(data);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    public boolean contains(int data) {
        int currentPos = findPos(data);
        return isActive(currentPos);
    }

    public void makeEmpty() {
        currentSize = 0;
        Arrays.fill(array, null);
    }

    //私有成员
    private static class HashEntry {
        public int data;
        public boolean isActive;

        public HashEntry(int data) {
            this.data = data;
            this.isActive = false;
        }

        public HashEntry(int data, boolean isActive) {
            this.data = data;
            this.isActive = isActive;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private int funcMode;

    private HashEntry[] array;

    private int currentSize;

    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    private int findPos(int data) {
        int currentPos = myhash(data);
        int offset=1;
            while(true){
                if(array[currentPos]==null)break;
                if(array[currentPos].data==data)break;
                if(funcMode==1)
                    currentPos++;
                else if(funcMode==2){
                    currentPos+=offset;
                    offset+=2;
                }
                else if(funcMode==3){
                    currentPos+=7-data%7;
                }
                if(currentPos>=array.length)
                    currentPos-=array.length;
            }


        return currentPos;
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private void rehash() {
        HashEntry[] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;
        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].data);
    }

    private int hash(int data) {
        return data % 11;
    }

    private int myhash(int data) {
        int hashVal = hash(data);
        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;
        return hashVal;
    }

    private static int nextPrime(int n) {
        if (isPrime(n)) return n;
        else return (n + 1);
    }

    private static boolean isPrime(int n) {
        return n % 2 == 1;
    }
}

