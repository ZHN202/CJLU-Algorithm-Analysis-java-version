public class Q3_1 {
    public static void main(String[] args) {
        MylinkedListStack<Integer> a = new MylinkedListStack<Integer>();
        System.out.println(a.getSize());
        a.push(1);
        a.push(2);
        a.push(3);
        System.out.println(a.getSize());
        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.getSize());
        System.out.println(a.pop());
    }
}


class MylinkedListStack<AnyType>{
    class Node<AnyType>{
        public AnyType data;
        public Node<AnyType> next;

        Node(AnyType data){
            this.data=data;
            this.next=null;
        }
        Node(){
            this.next=null;
        }
    }
    private Node top;
    int size=0;

    MylinkedListStack(){
        top=new Node();
    }
    public void push(AnyType data){
        Node node = new Node(data);
        node.next=top.next;
        top.next=node;
        size++;
    }
    public AnyType pop(){
        if(size==0){
            System.out.println("Stack is empty!!!");
            return null;
        }
        else{
            AnyType data= (AnyType) top.next.data;
            top.next=top.next.next;
            size--;
            return data;
        }
    }

    public int getSize() {
        return size;
    }
}