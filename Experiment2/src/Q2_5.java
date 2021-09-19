import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Q2_5 {
    public static void main(String[] args) {
        int n = 10000;
        List<Integer> L = createList(n);

    }
    static List createList(int n){
        Random r = new Random(n);
        List<Integer> L = new ArrayList<Integer>();
        for(int i=0;i<n;i++)
            L.add(r.nextInt(Integer.MAX_VALUE));
        return L;
    }
}
