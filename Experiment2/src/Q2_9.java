import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Q2_9 {
    public static void main(String[] args) {
        List<Integer> a1 = new ArrayList<Integer>();
        List<Integer> e1 = new ArrayList<Integer>();
        a1.add(1);
        a1.add(2);
        a1.add(3);
        a1.add(4);
        e1.add(0);
        e1.add(1);
        e1.add(3);
        e1.add(2);
        List<Integer> a2 = new ArrayList<Integer>();
        List<Integer> e2 = new ArrayList<Integer>();
        a2.add(1);
        a2.add(2);
        a2.add(3);
        a2.add(4);
        e2.add(0);
        e2.add(1);
        e2.add(3);
        e2.add(2);
        Polynomial p1 = new Polynomial(a1, e2);
        Polynomial p2 = new Polynomial(a2, e2);
        p1.printPolynomial();
        p2.printPolynomial();
        p1.add(p2);
        p1.printPolynomial();
        p2.multiply(p1);
        p2.printPolynomial();
    }
}

class Polynomial {
    private List<Integer> a = new ArrayList<Integer>();
    private List<Integer> e = new ArrayList<Integer>();

    Polynomial() {
    }

    Polynomial(List a, List e) {
        this.a = a;
        this.e = e;
    }

    public List getA() {
        return this.a;
    }

    public List getE() {
        return this.e;
    }

    public void printPolynomial() {
        int ai, ei;
        ListIterator<Integer> iterA = this.a.listIterator();
        ListIterator<Integer> iterE = this.e.listIterator();
        System.out.print("P(x)=");
        while (iterA.hasNext()) {
            ai = iterA.next();
            ei = iterE.next();
            if (ei == 0) System.out.print(ai);
            else System.out.print(ai + "x^" + ei);
            if (iterA.hasNext()) System.out.print("+");
        }
        System.out.println();
    }

    public void add(Polynomial p2) {
        ListIterator<Integer> iterE = p2.getE().listIterator();
        ListIterator<Integer> iterA = p2.getA().listIterator();
        while (iterE.hasNext()) {
            int ei2 = iterE.next();
            int ai2 = iterA.next();
            if (this.e.contains(ei2)) {
                int index1 = e.indexOf(ei2);
                int ai1 = a.get(index1);
                a.set(index1, ai1 + ai2);
            } else {
                e.add(ei2);
                a.add(ai2);
            }
        }
    }

    public void multiply(Polynomial p2) {
        Polynomial res = new Polynomial();

        ListIterator<Integer> iterE2 = p2.getE().listIterator();
        ListIterator<Integer> iterA2 = p2.getA().listIterator();
        while (iterE2.hasNext()) {
            List<Integer> thisA = clone(this.a);
            List<Integer> thisE = clone(this.e);
            Polynomial ptemp = new Polynomial(thisA, thisE);
            int ei2 = iterE2.next();
            int ai2 = iterA2.next();
            ListIterator<Integer> iterE1 = ptemp.e.listIterator();
            ListIterator<Integer> iterA1 = ptemp.a.listIterator();
            while (iterA1.hasNext()) {
                int ai1 = iterA1.next();
                int ei1 = iterE1.next();
                iterA1.previous();
                iterE1.previous();
                iterA1.set(ai1 * ai2);
                iterE1.set(ei1 + ei2);
                iterA1.next();
                iterE1.next();
            }
            res.add(ptemp);
        }
        this.e = res.getE();
        this.a = res.getA();
    }

    private List clone(List L) {
        List newL = new ArrayList();
        ListIterator iter = L.listIterator();
        while (iter.hasNext()) {
            newL.add(iter.next());
        }
        return newL;
    }

}