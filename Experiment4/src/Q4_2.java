public class Q4_2 {
}

class AVLtree<AnyType extends Comparable<? super AnyType>> {
    AvlNode<AnyType> root;
    private static final int ALLOWED_IMBALANCE = 1;

    private class AvlNode<AnyType extends Comparable<? super AnyType>> {
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

    private int height(AvlNode<AnyType> node) {
        return node == null ? -1 : node.height;
    }

    private AvlNode<AnyType> insert(AnyType data, AvlNode<AnyType> root) {
        if (root == null) return new AvlNode<>(data);
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

    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithLeftChild(k3.left);
        return rotateWithLeftChild(k3);
    }
    private AvlNode<AnyType> balance(AvlNode<AnyType> root) {
        if (root == null) return root;
        if (height(root.left) - height(root.right) > ALLOWED_IMBALANCE)
            if (height(root.left.left) >= height(root.right.right))
                root = rotateWithLeftChild(root);
            else
                root = doubleWithLeftChild(root);
        if (height(root.right) - height(root.left) > ALLOWED_IMBALANCE)
            if (height(root.right.right) >= height(root.left.left))
                root = rotateWithLeftChild(root);
            else
                root = doubleWithLeftChild(root);

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }


}