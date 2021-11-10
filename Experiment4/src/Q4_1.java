public class Q4_1 {
    public static void main(String[] args) {

    }
}
class ADTtree<AnyType extends Comparable<? super AnyType>>{
    private class treeNode<AnyType extends Comparable<? super AnyType>>{
        AnyType data;
        treeNode<AnyType> left;
        treeNode<AnyType> right;
        treeNode(AnyType data){
            this.data = data;
            left = null;
            right = null;
        }
        public treeNode<AnyType> insert(AnyType data, treeNode<AnyType> T){
            if(T==null)return new treeNode<>(data);
            int compareResult = data.compareTo(T.data);
            if(compareResult<0)T.left = T.insert(data,T.left);
            else if(compareResult>0)T.right = T.insert(data,T.right);
            return T;
        }
    }
    private treeNode<AnyType> root;
    private int nodeNumber=0;
    public ADTtree(){
        root = null;
    }
    public void insert(AnyType data){
        root.insert(data,root);
        nodeNumber++;
    }
    public int leavesNumber(){
        treeNode node = root;
        while(node.)
    }
}