
public class Q2_8 {
    public static void main(String[] args) {
        singlyList L = new singlyList();
        L.add(1);
        L.add(2);
        L.add(3);
        L.add(4);
        L.add(5);
        L.add(6);
        L.add(7);
        L.add(8);
        L.add(9);

        System.out.println("size:"+L.size());
        L.printList();
        L.addNodeIsNotContained(10);
        L.printList();
        L.remove(5);
        L.printList();
    }
}
class singlyList{
    private Node headNode = new Node();
    private Node node = headNode;


    singlyList(){
    }

    public Node getHeadNode(){
        return headNode;
    }
    public void add(int x){
        Node node = new Node(x);
        append(node);
    }
    public void append(Node node){
        this.node.next(node);
        this.node = node;
    }
    public int size(){
        Node head = new Node(getHeadNode());
        int cnt = 0;
        while(head.getNext()!=null){
            cnt++;
            head = head.getNext();
        }
        return cnt;
    }
    public void printList(){
        Node head = new Node(getHeadNode().getNext());
        while(true){
            if(head.getNext()==null)System.out.print(head.getData());
            else System.out.print(head.getData()+"->");
            head = head.getNext();
            if(head==null) break;

        }
        System.out.println();
    }
    public boolean isContain(int x){
        Node head = new Node(getHeadNode().getNext());
        while(head.hasNext()){
            if(head.getData() == x)return true;
            head = head.getNext();
        }
        return false;
    }
    public void addNodeIsNotContained(int x){
        if(!isContain(x)){
            Node node = new Node(x);
            append(node);
        }
    }
    public void remove(int x) {
        Node head = new Node(getHeadNode().getNext());
        while (head.hasNext()) {
            if (head.getNext().getData() == x) break;
            head = head.getNext();
        }
        head.next(head.getNext().getNext());
    }
}