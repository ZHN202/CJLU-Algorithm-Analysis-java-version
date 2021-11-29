public class Q4_3_ch {
    public static void main(String[] args) {
        AVLTree<Integer> aTree = new AVLTree<>();
        aTree.insert(3);
        aTree.insert(1);
        aTree.insert(4);
        aTree.insert(6);
        aTree.insert(9);
        aTree.insert(7);
        aTree.insert(5);
        aTree.insert(2);
        System.out.println("leaves number:" + aTree.getLeavesNumber());
        System.out.println("node number:" + aTree.getNodeNumber());
        System.out.println("tree height:" + aTree.getHeight());
        System.out.println("中序遍历：");
        aTree.inorderTraversal();
        System.out.println("后序遍历：");
        aTree.postorderTraversal();
        System.out.println("先序遍历：");
        aTree.preorderTraversal();
    }

}


class AVLTree<AnyType extends Comparable<? super AnyType>> {

    private static final int ALLOWED_IMBALANCE = 1;
    private int nodeNumber;
    private static class AvlNode<AnyType extends Comparable<? super AnyType>> {
        AnyType data;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;

        AvlNode(AnyType data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        AvlNode(AnyType data, AvlNode<AnyType> left, AvlNode<AnyType> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    AvlNode<AnyType> root;

    public AVLTree() {
        root = null;
        nodeNumber=0;
    }

    public void insert(AnyType data) {
        root = insert(data, root);
        nodeNumber++;
    }

    public int getNodeNumber(){
        return nodeNumber;
    }
    public int getLeavesNumber(){
        return leavesNumber(root);
    }

    public void preorderTraversal() {
        if (nodeNumber == 0) System.out.println("Tree is empty!!!");
        else preorder(root);
        System.out.println();
    }

    public void inorderTraversal() {
        if (nodeNumber == 0) System.out.println("Tree is empty!!!");
        else inorder(root);
        System.out.println();
    }

    public void postorderTraversal() {
        if (nodeNumber == 0) System.out.println("Tree is empty!!!");
        else postorder(root);
        System.out.println(

        );
    }
    public int getHeight(){
        return treeHeight(root);
    }
    private int height(AvlNode<AnyType> node) {
        return node == null ? -1 : node.height;
    }

    private AvlNode<AnyType> insert(AnyType data, AvlNode<AnyType> root) {
        if (root == null) return new AvlNode<>(data,null,null);
        int compareResult = data.compareTo(root.data);
        if (compareResult < 0) root.left = insert(data, root.left);
        else if (compareResult > 0) root.right = insert(data, root.right);
        return balance(root);
    }

    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2){
        AvlNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private AvlNode<AnyType> balance(AvlNode<AnyType> root) {
        if (root == null) return root;
        if (height(root.left) - height(root.right) > ALLOWED_IMBALANCE)
            if (height(root.left.left) >= height(root.left.right))
                root = rotateWithLeftChild(root);
            else
                root = doubleWithLeftChild(root);
        if (height(root.right) - height(root.left) > ALLOWED_IMBALANCE)
            if (height(root.right.right) >= height(root.right.left))
                root = rotateWithRightChild(root);
            else
                root = doubleWithRightChild(root);

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }
    private int leavesNumber(AvlNode<AnyType> root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;
        return (leavesNumber(root.left) + leavesNumber(root.right));
    }
    private void preorder(AvlNode<AnyType> root) {
        if (root != null){
            System.out.print(root.data + "->");
            preorder(root.left);
            preorder(root.right);
        }
    }

    private void inorder(AvlNode<AnyType> root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + "->");
            inorder(root.right);
        }
    }

    private void postorder(AvlNode<AnyType> root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + "->");


        }
    }

    private int treeHeight(AvlNode<AnyType> root) {
        if (root == null) return 0;
        else {
            int leftHeight = treeHeight(root.left);
            int rightHeight = treeHeight(root.right);
            if (leftHeight > rightHeight) return (leftHeight + 1);
            else return (rightHeight + 1);
        }
    }

}