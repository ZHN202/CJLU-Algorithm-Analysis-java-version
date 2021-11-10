public class Q2_6 {
    public static void main(String[] args) {
        int N = 10000000;
        Node[] node = new Node[N];
        Node Head = new Node();
        node[0] = new Node(0);
        Head.next(node[0]);

        for(int i=0;i<N-1;i++){
            node[i+1] = new Node(i+1);
            node[i].next(node[i+1]);
        }

        long startTime = System.currentTimeMillis(); //获取开始时间

        Node head;
        head = reverseList(Head);
        System.out.println("newList:");
        while (head.hasNext()) {
            head = head.getNext();
            System.out.println(head.getData());
        }

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("ArrayList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
    }

    // 返回新的头结点
    public static Node reverseList(Node head) {
        if (head.getNext() == null) {
            return head;
        }
        Node prevNode = head;//前一个指针
        Node curNode = head.getNext();//后一个指针
        Node next;
        while (curNode.getNext() != null) {
            next = curNode.getNext();
            curNode.next(prevNode);
            prevNode = curNode;
            curNode = next;
        }
        head.next(null);
        Node newHead = new Node();
        newHead.next(prevNode);
        return newHead;
    }
}

class Node {
    private Node next = null;
    private int data;

    Node(int data) {
        this.data = data;
    }

    Node(Node node) {
        this.data = node.getData();
        this.next = node.getNext();
    }

    Node() {
    }

    public void next(Node node) {
        this.next = node;
    }

    public int getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public boolean hasNext() {
        if (this.next.getNext() == null) return false;
        return true;
    }

}
