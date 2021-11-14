public class Q4_1 {
    public static void main(String[] args) {
        ADTtree<Integer> aTree = new ADTtree<>();
        aTree.insert(3);
        aTree.insert(1);
        aTree.insert(4);
        aTree.insert(6);
        aTree.insert(9);
        aTree.insert(7);
        aTree.insert(5);
        aTree.insert(2);
        System.out.println("leaves number:" + aTree.leavesNumber());
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

class ADTtree<AnyType extends Comparable<? super AnyType>> {
    private class treeNode<AnyType extends Comparable<? super AnyType>> {
        AnyType data;
        public treeNode<AnyType> left;
        public treeNode<AnyType> right;

        treeNode(AnyType data) {
            this.data = data;
            left = null;
            right = null;
        }

    }

    private treeNode<AnyType> root;
    private int nodeNumber = 0;

    public ADTtree() {
        root = null;
    }

    public void insert(AnyType data) {
        root = insert(data, root);
        nodeNumber++;
    }

    public int leavesNumber() {
        return leavesNumber(root);
    }

    public int getHeight() {
        return Height(root);
    }

    public int getNodeNumber() {
        return nodeNumber;
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
        System.out.println();
    }

    private treeNode<AnyType> insert(AnyType data, treeNode<AnyType> T) {
        if (T == null) return new treeNode<AnyType>(data);
        int compareResult = data.compareTo(T.data);
        if (compareResult < 0) T.left = insert(data, T.left);
        else if (compareResult > 0) T.right = insert(data, T.right);
        return T;
    }

    private int leavesNumber(treeNode<AnyType> root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;
        return (leavesNumber(root.left) + leavesNumber(root.right));
    }

    private int Height(treeNode<AnyType> root) {
        if (root == null) return 0;
        else {
            int leftHeight = Height(root.left);
            int rightHeight = Height(root.right);
            if (leftHeight > rightHeight) return (leftHeight + 1);
            else return (rightHeight + 1);
        }
    }

    private void preorder(treeNode<AnyType> root) {
        if (root == null) System.out.println();
        else {
            inorder(root.left);
            System.out.print(root.data + "->");

            inorder(root.right);
        }
    }

    private void inorder(treeNode<AnyType> root) {
        if (root != null) {
            System.out.print(root.data + "->");
            inorder(root.left);
            inorder(root.right);
        }
    }

    private void postorder(treeNode<AnyType> root) {
        if (root != null) {
            inorder(root.right);
            System.out.print(root.data + "->");
            inorder(root.left);

        }
    }
}