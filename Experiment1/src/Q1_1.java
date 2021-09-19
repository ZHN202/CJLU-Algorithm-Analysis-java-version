public class Q1_1 {
    public static void main(String[] args) throws Exception {
        System.out.println(method_a(1000));
        System.out.println(method_a(10000));
        System.out.println(method_a(100000));
        System.out.println(method_a(1000000));
        System.out.println(method_b(1000));
        System.out.println(method_b(10000));
        //System.out.println(method_b(100000));
        //System.out.println(method_b(1000000));
    }

    static int method_a(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++)
            sum += i;
        return sum;
    }

    static int method_b(int n) {
        if (n == 1) return 1;
        else return n + method_b(n - 1);
    }
}
