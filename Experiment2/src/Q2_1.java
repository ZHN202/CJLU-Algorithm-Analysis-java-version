import java.util.*;

public class Q2_1 {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis(); //获取开始时间
        printLots_A();
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("ArrayList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        printLots_L();
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("LinkedList程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        
        
    }
    static void printLots_A(){
        List<Integer> L = new ArrayList<Integer>();
        List<Integer> P = new ArrayList<Integer>();
        Random r = new Random();
        for(int i=0;i<100000;i++)
            L.add(i+6);
        for(int i=0;i<50000;i++)
            P.add(r.nextInt(100000));
        Iterator<Integer> iter = P.iterator(); 
        for(int i=0;i<50000;i++)
            L.get(iter.next());

    }
    static void printLots_L(){
        List<Integer> L = new LinkedList<Integer>();
        List<Integer> P = new LinkedList<Integer>();
        Random r = new Random();
        for(int i=0;i<100000;i++)
            L.add(i+6);
        for(int i=0;i<50000;i++)
            P.add(r.nextInt(100000));
        Iterator<Integer> iter = P.iterator(); 
        for(int i=0;i<50000;i++)
            L.get(iter.next());

    }
}
