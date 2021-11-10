public class Q3_2 {
    public static void main(String[] args) {
        MyLinkedListQueue<Integer> a= new MyLinkedListQueue<>();
        a.enqueue(1);
        a.enqueue(2);
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());

        MyArrayQueue<Integer> b= new MyArrayQueue<>(1000);
        b.enqueue(1);
        b.enqueue(2);
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
        MyCircularArrayQueue<Integer> c= new MyCircularArrayQueue<>(1000);
        c.enqueue(1);
        c.enqueue(2);
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
    }
}
class MyLinkedListQueue<AnyType>{
    private class Node<AnyType>{
        Node next;
        AnyType data;
        Node(){next=null;}
        Node(AnyType data){
            this.data=data;
            next=null;
        }
    }
    private int size;
    Node head;
    Node tail;

    MyLinkedListQueue(){
        head=new Node();
        tail=new Node();
        size=0;
    }
    public void enqueue(AnyType data){
        Node node=new Node(data);
        if(size==0){
            tail.next=node;
            head.next=node;
        }
        else{
            tail.next.next=node;
            tail.next=node;
        }
        size++;
    }
    public AnyType dequeue(){
        if(size==0){
            System.out.println("Queue is empty!!!");
            return null;
        }
        AnyType data= (AnyType) head.next.data;
        head.next=head.next.next;
        size--;
        return data;
    }
    public AnyType getTail(){
        return (AnyType) tail.next.data;
    }
    public void setTail(AnyType data){
        tail.next.data=data;
    }
    public void print(){
        Node next=new Node();
        next=head.next;
        while(next!=tail.next){
            System.out.print(next.data+"->");
            next = next.next;
        }
        System.out.println(next.data);
    }
    public int getSize() {
        return size;
    }
    public boolean empty(){
        if(size==0)return true;
        return false;
    }
}


class MyArrayQueue<AnyType>{
    private AnyType[] array;
    private int head;
    private int tail;
    private int size;

    MyArrayQueue(int initialCapacity){
        array = (AnyType[]) new Object[initialCapacity];
        head=0;
        tail=0;
        size=0;
    }
    public void enqueue(AnyType data){
        if(size==0)array[0]=data;
        else{
            array[tail+1]=data;
            tail++;
        }
        size++;
    }
    public AnyType dequeue(){
        if(size==0){
            System.out.println("Queue is empty!!!");
            return null;
        }
        else{
            AnyType data=array[head];
            head++;
            size--;
            return data;
        }
    }

    public int getSize() {
        return size;
    }
}

class MyCircularArrayQueue<AnyType>{
    private AnyType[] array;
    private int head;
    private int tail;
    private int size;

    MyCircularArrayQueue(int initialCapacity){
        array = (AnyType[]) new Object[initialCapacity];
        head=0;
        tail=0;
        size=0;
    }

    public void enqueue(AnyType data){
        if(size==array.length){
            System.out.println("请申请更大内存的队列！！！");
        }
        else {
            if (size == 0) array[0] = data;
            else {
                array[tail % array.length + 1] = data;
                tail++;
                tail %= array.length;
            }
            size++;
        }
    }
    public AnyType dequeue(){
        if(size==0){
            System.out.println("Queue is empty!!!");
            return null;
        }
        else{
            AnyType data=array[head%array.length];
            head++;
            head%=array.length;
            size--;
            return data;
        }
    }

    public int getSize() {
        return size;
    }
}