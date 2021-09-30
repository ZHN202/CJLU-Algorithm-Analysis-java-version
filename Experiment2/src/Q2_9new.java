public class Q2_9new {
    public static void main(String[] args) {
        NewPolynomial p1 = new NewPolynomial();
        p1.append(10,1000);
        p1.append(5,14);
        p1.append(1,0);
        NewPolynomial p2 = new NewPolynomial();
        p2.append(3,1990);
        p2.append(-2,1492);
        p2.append(11,1);
        p2.append(5,0);
        NewPolynomial p3 = new NewPolynomial();
        p3.append(10,1000);
        p3.append(5,14);
        p3.append(1,0);
        p1.printPolynomial();
        p2.printPolynomial();
        p1.add(p2);
        p1.printPolynomial();
        p3.multiply(p2);
        p3.printPolynomial();
    }
}

class Item {
    int a;
    int e;
    Item next;

    Item(int a, int e) {
        this.a = a;
        this.e = e;
    }
    Item(){}
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
            Item headThis = this.head;
            int e = headP.e;
            while (headThis.next != null) {
                headThis = headThis.next;
                if (headThis.e == e) {
                    headThis.a = headP.a + headThis.a;
                    break;
                }
            }
            if(headThis.next==null)this.append(headP.a,headP.e);
        }
    }

    public void multiply(NewPolynomial p) {
        Item headP = p.head;


        NewPolynomial thisP = new NewPolynomial();
        while(headP.next!=null){
            headP = headP.next;
            Item headThis = head;
            NewPolynomial temp = new NewPolynomial();
            while(headThis.next!=null){
                headThis = headThis.next;
                int a = headThis.a*headP.a;
                int e = headThis.e+headP.e;
                temp.append(a,e);
            }
            thisP.add(temp);
        }
        this.head.next = thisP.head.next;
    }
    public void printPolynomial(){
        Item headThis = head;
        while(headThis.next!=null){
            headThis = headThis.next;
            if(headThis.e==0)System.out.print(headThis.a);
            else System.out.print(headThis.a+"x^"+ headThis.e);
            if(headThis.next!=null)System.out.print('+');
        }
        System.out.println();
    }
}