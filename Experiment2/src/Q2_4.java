import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Q2_4 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //获取开始时间
        method_A(100000);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("ArrayList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        method_L(100000);
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("LinkedList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
    }

    static void method_A(int n) {
        List<Integer> L = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < n; i++)
            L.add(r.nextInt(Integer.MAX_VALUE));
        int maxnum = Integer.MIN_VALUE, minnum = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int num = L.get(i);
            if (num > maxnum) maxnum = num;
            if (num < minnum) minnum = num;
            sum += num;
        }
        double average = sum / n;
        System.out.println("max:" + maxnum + "  min:" + minnum + "  average:" + average);
    }

    static void method_L(int n) {
        List<Integer> L = new LinkedList<Integer>();
        Random r = new Random();
        for (int i = 0; i < n; i++)
            L.add(r.nextInt(Integer.MAX_VALUE));
        int maxnum = Integer.MIN_VALUE, minnum = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int num = L.get(i);
            if (num > maxnum) maxnum = num;
            if (num < minnum) minnum = num;
            sum += num;
        }
        double average = sum / n;
        System.out.println("max:" + maxnum + "  min:" + minnum + "  average:" + average);
    }

}
