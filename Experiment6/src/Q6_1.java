import java.nio.BufferUnderflowException;
import java.util.Random;

public class Q6_1 {
    public static void main(String[] args) {
        Random r =new Random();
        BinaryHeap<Integer> binaryHeap=new BinaryHeap<>();
        for(int i=0;i<100;i++ )
            binaryHeap.insert(r.nextInt());
        System.out.println(binaryHeap.findMin());
    }
}

class BinaryHeap<AnyType extends Comparable<? super AnyType>>{
    public BinaryHeap(){
        array=(AnyType[]) new Comparable[(DEFAULT_CAPACITY+2)*11/10];
        currentSize=0;
    }
    public BinaryHeap(int capacity){
        array=(AnyType[]) new Comparable[(currentSize+2)*11/10];
        currentSize=0;
    }
    public BinaryHeap(AnyType [] items){
        currentSize=items.length;
        array=(AnyType[]) new Comparable[(currentSize+2)*11/10];
        int i=1;
        for(AnyType item:items)
            array[i++]=item;
        builHeap();
    }

    public void insert(AnyType x){

        if(currentSize== array.length-1)
            enlargeArray(array.length*2+1);

        int hole = ++currentSize;
        for(array[0]=x;x.compareTo(array[hole/2])<0;hole/=2)
            array[hole]=array[hole/2];
        array[hole]=x;

    }
    public AnyType findMin() {
        return array[1];
    }
    public AnyType deleteMin(){

        if(isEmpty())
            throw new BufferUnderflowException();

        AnyType minItem =findMin();
        array[1]=array[currentSize--];
        percolateDown(1);
        return minItem;

    }
    public boolean isEmpty(){
        return currentSize==0;
    }

    private static final int DEFAULT_CAPACITY=10;

    private int currentSize;
    private AnyType[] array;
    private void percolateDown(int hole){
        int child;
        AnyType tmp=array[hole];
        for(;hole*2<=currentSize;hole=child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole]=tmp;
    }
    private void builHeap(){
        for(int i=currentSize/2;i>0;i--)
            percolateDown(i);
    }
    private void enlargeArray(int newSize){
        AnyType[] newArray= (AnyType[]) new Comparable[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array=newArray;
    }
}