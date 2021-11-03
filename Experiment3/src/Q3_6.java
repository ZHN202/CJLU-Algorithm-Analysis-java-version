import java.util.*;

public class Q3_6 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue=new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(stack.toString());
        System.out.println(queue.toString());
        stack.pop();
        stack.pop();
        queue.remove();
        queue.remove();
        stack.push(5);
        queue.add(5);
        System.out.println(stack.toString());
        System.out.println(queue.toString());
    }
}
