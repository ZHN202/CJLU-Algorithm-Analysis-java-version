import java.util.*;


public class Q2_5 {
    public static void main(String[] args) {
        int n = 10000;
        List L = createList(n,"arrayList");
        ListIterator iter = L.listIterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    static List createList(int n,String listType){
        Random r = new Random(n);
        List<Integer> L = null;
        if(listType.equals("arrayList"))L = new ArrayList<Integer>();
        else if(listType.equals("linkedList"))L = new LinkedList<Integer>();
        for(int i=0;i<n;i++) {
            L.add(r.nextInt(Integer.MAX_VALUE));
        }
        return L;
    }
}
