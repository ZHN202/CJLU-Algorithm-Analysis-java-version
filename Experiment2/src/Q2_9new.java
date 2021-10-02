import java.util.Random;

public class Q2_9new {
    public static void main(String[] args) {
        int N = 10000;
        Random r = new Random();
        NewPolynomial p1 = new NewPolynomial();
        for(int i=0;i<N;i++){
            p1.append(r.nextInt(Integer.MAX_VALUE), r.nextInt(Integer.MAX_VALUE));
        }

        NewPolynomial p2 = new NewPolynomial();
        for(int i=0;i<N;i++){
            p2.append(r.nextInt(Integer.MAX_VALUE), r.nextInt(Integer.MAX_VALUE));
        }
        NewPolynomial p3 = new NewPolynomial();
        for(int i=0;i<N;i++){
            p3.append(r.nextInt(Integer.MAX_VALUE), r.nextInt(Integer.MAX_VALUE));
        }
        //p1.printPolynomial();
        //p2.printPolynomial();

        // 测试程序运行时间
        long startTime = System.currentTimeMillis(); //获取开始时间
        p1.add(p2);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("ArrayList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        //p1.printPolynomial();

        // 测试程序运行时间
        startTime = System.currentTimeMillis(); //获取开始时间
        p3.multiply(p2);
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("LinkedList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        //p3.printPolynomial();
    }
}

class Item {
    private int a;
    private int e;
    Item next;

    Item(int a, int e) {
        this.a = a;
        if (a == 0) this.e = 0;
        else this.e = e;

    }

    Item() {
    }

    public int getA() {
        return this.a;
    }

    public int getE() {
        return this.e;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setE(int e) {
        this.e = e;
    }
}

class NewPolynomial {
    Item next = new Item();
    Item head = new Item();

    NewPolynomial() {
        next = head;
    }

    public void append(int a, int e) {
        Item i = new Item(a, e);
        next.next = i;
        next = i;
    }

    public void add(NewPolynomial p) {

        Item headP = p.head;
        while (headP.next != null) {
            headP = headP.next;
            if (headP.getA() == 0) headP.setE(0);
            Item headThis = this.head;
            int e = headP.getE();
            while (headThis.next != null) {
                headThis = headThis.next;
                if (headThis.getE() == e) {
                    headThis.setA(headP.getA() + headThis.getA());
                    break;
                }
            }
            if (headThis.next == null) this.append(headP.getA(), headP.getE());

        }
    }

    public void multiply(NewPolynomial p) {
        Item headP = p.head;


        NewPolynomial thisP = new NewPolynomial();
        while (headP.next != null) {
            headP = headP.next;
            Item headThis = head;
            NewPolynomial temp = new NewPolynomial();
            while (headThis.next != null) {
                headThis = headThis.next;
                int a = headThis.getA() * headP.getA();
                int e = headThis.getE() + headP.getE();
                temp.append(a, e);
            }
            thisP.add(temp);
        }
        this.head.next = thisP.head.next;
    }

    public void printPolynomial() {
        Item headThis = head;
        boolean flag = true;
        while (headThis.next != null) {
            headThis = headThis.next;
            if (headThis.getA() == 0 && flag) {
                System.out.print('0');
                flag = false;
            } else if (headThis.getA() != 0) {
                if (headThis.getE() == 0) System.out.print(headThis.getA());
                else System.out.print(headThis.getA() + "x^" + headThis.getE());
            }
            if (headThis.next != null && flag)
                if (headThis.next.getA() >= 0) System.out.print('+');
        }
        System.out.println();
    }
}