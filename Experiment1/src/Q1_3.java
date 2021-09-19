public class Q1_3 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //获取开始时间
        System.out.println(method_a(100));
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        System.out.println(method_b(50));
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
    }

    static long method_a(int n) {
        long[] fib = new long[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) fib[0] = 1;
            else if (i == 1) fib[1] = 1;
            else fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n - 1];
    }

    static long method_b(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 1;
        else return method_b(n - 1) + method_b(n - 2);
    }
}
