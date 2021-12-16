import java.util.*;

public class Q7_3 {
    public static void main(String[] args) {
        gph g = new gph();
        g.insertEdge("D", "A", 4);
        g.insertEdge("D", "E", 5);
        g.insertEdge("D", "H", 6);
        g.insertEdge("A", "B", 3);
        g.insertEdge("A", "E", 4);
        g.insertEdge("H", "E", 2);
        g.insertEdge("H", "I", 4);
        g.insertEdge("E", "B", 2);
        g.insertEdge("E", "F", 11);
        g.insertEdge("E", "I", 1);
        g.insertEdge("B", "C", 10);
        g.insertEdge("B", "F", 3);
        g.insertEdge("I", "F", 3);
        g.insertEdge("I", "J", 7);
        g.insertEdge("F", "C", 6);
        g.insertEdge("F", "G", 2);
        g.insertEdge("F", "J", 11);
        g.insertEdge("C", "G", 1);
        g.insertEdge("J", "G", 8);
        System.out.print("kruskal:");
        g.kruskal();
        System.out.print("prim:");
        g.prim();

    }
}

class gph {
    private static class edge {

        public String x;
        public String y;
        public int weight;

        public edge(String x, String y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        static Comparator<edge> cmp = new Comparator<edge>() {
            @Override
            public int compare(edge e1, edge e2) {
                // TODO Auto-generated method stub
                return e1.weight - e2.weight;
            }
        };

        public String toString() {
            return x + "-" + y + " " + weight + "  ";
        }
    }

    public gph() {
        edges = new HashMap<>();
        edgesR = new HashMap<>();
    }

    public void insertEdge(String source, String sink, int capacity) {
        if (!edges.containsKey(source)) edges.put(source, new LinkedList<>());
        edges.get(source).add(new edge(source, sink, capacity));
        if (!edgesR.containsKey(source)) edgesR.put(source, new LinkedList<>());
        if (!edgesR.containsKey(sink)) edgesR.put(sink, new LinkedList<>());
        edgesR.get(source).add(new edge(source, sink, capacity));
        edgesR.get(sink).add(new edge(sink, source, capacity));
    }

    public LinkedList<gph.edge> kruskal() {
        Queue<edge> es = sort(edges);
        LinkedList<gph.edge> minTree = new LinkedList<>();
        LinkedList<Set<String>> trees = new LinkedList<>();
        Set<String> points = new HashSet<>();
        edge e = es.remove();
        minTree.add(e);
        Set<String> v = new HashSet<>();
        v.add(e.x);
        v.add(e.y);
        points.add(e.x);
        points.add(e.y);
        trees.add(v);
        int sum = e.weight;
        while (!es.isEmpty()) {
            e = es.remove();
            if (!points.contains(e.x) && !points.contains(e.y)) {
                Set<String> ve = new HashSet<>();
                ve.add(e.x);
                ve.add(e.y);
                trees.add(ve);
                points.add(e.x);
                points.add(e.y);
                minTree.add(e);
                sum += e.weight;
            } else if (points.contains(e.x) && points.contains(e.y)) {
                Set<String> Sx = new HashSet<>(), Sy = new HashSet<>();
                for (Set<String> i : trees)
                    if (i.contains(e.x) && !i.contains(e.y)) Sx = i;
                    else if (!i.contains(e.x) && i.contains(e.y)) Sy = i;
                    else if (i.contains(e.x) && i.contains(e.y)) break;

                if (!Sx.isEmpty() && !Sy.isEmpty()) {
                    Sx.addAll(Sy);
                    Sy.clear();
                    minTree.add(e);
                    sum += e.weight;
                }
            } else {
                for (Set<String> i : trees)
                    if (i.contains(e.x) || i.contains(e.y)) {
                        i.add(e.x);
                        i.add(e.y);
                        break;
                    }
                points.add(e.x);
                points.add(e.y);
                minTree.add(e);
                sum += e.weight;
            }
        }
        System.out.println(sum);
        return minTree;
    }

    public LinkedList<gph.edge> prim() {
        String start="A";
        int sum=0;
        Set<String> vs=edgesR.keySet();
        Set<String> newVs=new HashSet<>();
        newVs.add(start);
        Queue<edge> es = new PriorityQueue<>(edge.cmp);
        es.addAll(edgesR.get(start));
        LinkedList<gph.edge> minTree = new LinkedList<>();
        while(!newVs.containsAll(vs)){
            edge e=es.remove();
            if(newVs.contains(e.x)&& !newVs.contains(e.y)){
                es.addAll(edgesR.get(e.y));
                newVs.add(e.y);
                minTree.add(e);
                sum+=e.weight;
            }
        }
        System.out.println(sum);
        return minTree;
    }

    private final Map<String, LinkedList<gph.edge>> edges;
    private final Map<String, LinkedList<gph.edge>> edgesR;

    private Queue<edge> sort(Map<String,LinkedList<edge>> ed) {

        Queue<edge> es = new PriorityQueue<>(edge.cmp);
        for (LinkedList<gph.edge> e : ed.values()) {
            es.addAll(e);
        }
        return es;
    }
}