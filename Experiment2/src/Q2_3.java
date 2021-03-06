import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q2_3 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //获取开始时间
        method_A();
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("ArrayList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        method_L();
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("LinkedList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        method_A_API();
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("ArrayList_API程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        method_L_API();
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("LinkedList_API程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
    }

    static void method_A(){
        List<Integer> L1 = new ArrayList<Integer>();
        List<Integer> L2 = new ArrayList<Integer>();
        List<Integer> L1andL2 = new ArrayList<Integer>();
        for(int i=0;i<100000;i++){
            L1.add(i+5);
            L2.add(i+10);
        }
        L1andL2.addAll(L1);
        for(int i=0;i<L2.size();i++)
            if(!L1andL2.contains(L2.get(i)))L1andL2.add(L2.get(i));
    }
    static void method_L(){
        List<Integer> L1 = new LinkedList<Integer>();
        List<Integer> L2 = new LinkedList<Integer>();
        List<Integer> L1andL2 = new LinkedList<Integer>();
        for(int i=0;i<100000;i++){
            L1.add(i+5);
            L2.add(i+10);
        }
        L1andL2.addAll(L1);
        for(int i=0;i<L2.size();i++)
            if(!L1andL2.contains(L2.get(i)))L1andL2.add(L2.get(i));
    }
    static void method_A_API(){
        List<Integer> L1 = new ArrayList<Integer>();
        List<Integer> L2 = new ArrayList<Integer>();
        List<Integer> L1andL2 = new ArrayList<Integer>();
        for(int i=0;i<100000;i++){
            L1.add(i+5);
            L2.add(i+10);
        }
        L1andL2.addAll(L1);
        L1andL2.removeAll(L2);
        L1andL2.addAll(L2);
    }
    static void method_L_API(){
        List<Integer> L1 = new LinkedList<Integer>();
        List<Integer> L2 = new LinkedList<Integer>();
        List<Integer> L1andL2 = new LinkedList<Integer>();
        for(int i=0;i<100000;i++){
            L1.add(i+5);
            L2.add(i+10);
        }
        L1andL2.addAll(L1);
        L1andL2.removeAll(L2);
        L1andL2.addAll(L2);
    }
}
