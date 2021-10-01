public class Q2_6 {
    public static void main(String[] args) {
        Node head = new Node();
        Node node1 = new Node(1);
        head.next(node1);
        Node node2 = new Node(2);
        node1.next(node2);
        Node node3 = new Node(3);
        node2.next(node3);
        Node node4 = new Node(4);
        node3.next(node4);
        Node tail = new Node();
        node4.next(tail);
        Node head1 = head;
        Node head2 = new Node();
        long startTime = System.currentTimeMillis(); //获取开始时间

        while (head1.hasNext()) {
            head1 = new Node(head1.getNext());
            System.out.println(head1.getData());
        }
        head = reverseList(head);
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
