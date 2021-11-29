import java.util.Arrays;

public class Q5_3 {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(33);
        for (int i = 1; i <= 20; i++)
            hashTable.insert(i);
        System.out.print("find 3:");
        System.out.print(hashTable.find(3) + "\n");
        System.out.print("find 15:");
        System.out.print(hashTable.find(15) + "\n");
        System.out.print("find 13:");
        System.out.print(hashTable.find(13) + "\n");
        System.out.print("find 100:");
        System.out.print(hashTable.find(100) + "\n");
    }


}

class HashTable {
    //公有成员
    public HashTable() {
        this(DEFAULT_TABLE_SIZE);

    }

    public HashTable(int size) {
        allocateArray(size);
        makeEmpty();
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

    public int find(int data) {
        return isActive(findPos(data)) ? findPos(data) : -1;
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

    private HashEntry[] array;

    private int currentSize;

    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    private int findPos(int data) {
        int currentPos = myhash(data);
        int offset = 1;
        while (true) {
            if (array[currentPos] == null) break;
            if (array[currentPos].data == data) break;
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
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

