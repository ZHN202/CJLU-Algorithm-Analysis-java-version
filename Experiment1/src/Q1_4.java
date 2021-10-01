import java.util.Random;

public class Q1_4 {
    public static void main(String[] args) {
        int[] a = new int[100000];
        long startTime = System.currentTimeMillis(); //获取开始时间
        method_a(a, 100000);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        method_b(a, 100000);
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        method_c(a, 100000);
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间


    }

    static void method_a(int[] a, int n) {
        Random r = new Random(n);
        for (int i = 0; i < n; i++) {
            int rnum = r.nextInt(n);
            for (int j = 0; j <= i; j++) {
                if (rnum == a[j]) {
                    rnum = r.nextInt(n);
                    j = 0;
                }
            }
            a[i] = rnum;
        }
    }

    static void method_b(int[] a, int n) {
        boolean[] used = new boolean[n];
        Random r = new Random(n);
        for (int i = 0; i < n; i++) {
            int rnum = r.nextInt(n);
            if (i == 0) {
                a[i] = rnum;
                used[rnum] = true;
            } else {
                while (used[rnum]) {
                    rnum = r.nextInt(n);
                }
                a[i] = rnum;
                used[rnum] = true;
            }
        }
    }

    static void method_c(int[] a, int n) {
        Random r = new Random(n);
        for (int i = 0; i < n; i++)
            a[i] = i + 1;
        for (int i = 1; i < n; i++) {
            int j = r.nextInt(n);
            swapReferences(a, i, j);
        }
    }

    static void swapReferences(int[] a, int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }
}
