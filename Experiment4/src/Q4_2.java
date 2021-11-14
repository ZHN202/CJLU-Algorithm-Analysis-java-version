public class Q4_2 {
}
class AVLtree<AnyType extends Comparable<? super AnyType>>{
    AvlNode<AnyType> root;
    private static final int ALLOWED_IMBALANCE = 1;
    private class AvlNode<AnyType extends Comparable<? super AnyType>>{
        AnyType data;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;
        AvlNode(AnyType data){
            this.data = data;
            this.left=null;
            this.right=null;
        }
        AvlNode(AnyType data, AvlNode<AnyType> left,AvlNode<AnyType> right){
            this.data = data;
            this.left=left;
            this.right=right;
            this.height=0;
        }
    }
    private int height(AvlNode<AnyType> node){
        return node==null?-1:node.height;
    }
    private AvlNode<AnyType> insert(AnyType data,AvlNode<AnyType> root){
        if(root==null)return new AvlNode<>(data);
        int compareResult = data.compareTo(root.data);
        if(compareResult<0)root.left = insert(data,root.left);
        else if(compareResult>0)root.right = insert(data,root.right);
        return balance(root);
    }
    private AvlNode<AnyType> balance(AvlNode<AnyType> root){
        if(root==null)return root;
        if(height(root.left) - height(root.right)>ALLOWED_IMBALANCE)
            if(height(root.left.left)>=height(root.right.right))
                root=rotateWithLeftChild(root);
            else
                root=doubleWithLeftChild(root);
        if(height(root.right)-height(root.left)>ALLOWED_IMBALANCE)
            if(height(root.right.right)>=height(root.left.left))
                root = rotateWithRightChild(root);
            else
                root=doubleWithRightChild(root);

        root.height = Math.max(height(root.left),height(root.right))+1;
        return root;
    }
}