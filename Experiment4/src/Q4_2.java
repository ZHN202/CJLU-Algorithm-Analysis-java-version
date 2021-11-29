public class Q4_2 {
    public static void main(String[] args) {
        ADTtree2<Integer> aTree = new ADTtree2<>();
        aTree.insert(3);
        aTree.insert(1);
        aTree.insert(4);
        aTree.insert(6);
        aTree.insert(9);
        aTree.insert(7);
        aTree.insert(5);
        aTree.insert(2);
        System.out.println("node number:" + aTree.getNodeNumber());
        System.out.println("中序遍历：");
        aTree.inorderTraversal();
        System.out.println("后序遍历：");
        aTree.postorderTraversal();
        System.out.println("先序遍历：");
        aTree.preorderTraversal();

        System.out.println("删除9");
        aTree.removeLazy(9);
        System.out.println("node number:" + aTree.getNodeNumber());
        System.out.println("中序遍历：");
        aTree.inorderTraversal();
        System.out.println("后序遍历：");
        aTree.postorderTraversal();
        System.out.println("先序遍历：");
        aTree.preorderTraversal();

        System.out.println("删除7");
        aTree.removeLazy(7);
        System.out.println("node number:" + aTree.getNodeNumber());
        System.out.println("中序遍历：");
        aTree.inorderTraversal();
        System.out.println("后序遍历：");
        aTree.postorderTraversal();
        System.out.println("先序遍历：");
        aTree.preorderTraversal();
    }
}


class ADTtree2<AnyType extends Comparable<? super AnyType>> {
    private class treeNode<AnyType extends Comparable<? super AnyType>> {
        AnyType data;
        public treeNode<AnyType> left;
        public treeNode<AnyType> right;
        public boolean delete;

        treeNode(AnyType data) {
            this.data = data;
            delete = false;
            left = null;
            right = null;
        }

    }

    private treeNode<AnyType> root;
    private int nodeNumber = 0;

    public ADTtree2() {
        root = null;
    }

    public void insert(AnyType data) {
        root = insert(data, root);
        nodeNumber++;
    }

    public void removeLazy(AnyType data){
        root = remove_lazy(data,root);
        nodeNumber--;
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
        else if(T.delete)T.delete=false;
        return T;
    }

    private treeNode<AnyType> remove_lazy(AnyType data,treeNode<AnyType> T){
        if(T==null)return null;
        int compareResult = data.compareTo(T.data);
        if(compareResult<0)T.left = remove_lazy(data,T.left);
        else if(compareResult>0)T.right=remove_lazy(data,T.right);
        else if(!T.delete)T.delete=true;
        return T;
    }


    private void preorder(treeNode<AnyType> root) {
        if (root != null){
            if(!root.delete) System.out.print(root.data + "->");
            preorder(root.left);
            preorder(root.right);
        }
    }

    private void inorder(treeNode<AnyType> root) {
        if (root != null) {
            inorder(root.left);
            if(!root.delete) System.out.print(root.data + "->");
            inorder(root.right);
        }
    }

    private void postorder(treeNode<AnyType> root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            if(!root.delete) System.out.print(root.data + "->");
        }
    }
}