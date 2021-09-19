public class Q1_2 {
    public static void main(String[] args) {
        System.out.println(method_a(1000));
        System.out.println(method_a(10000));
        System.out.println(method_a(100000));
        System.out.println(method_a(1000000));
        System.out.println(method_b(1000));
        System.out.println(method_b(10000));
    }

    static long method_a(int n) {
        int fac = 1;
        for (int i = 1; i <= n; i++)
            fac *= i;
        return fac;
    }

    static long method_b(int n) {
        if (n == 1) return 1;
        else return n * method_b(n - 1);
    }
}
