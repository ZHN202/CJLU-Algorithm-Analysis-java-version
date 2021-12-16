import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class Q7_4 {
    public static void main(String[] args) {
        flowNetwork fn = new flowNetwork();
        fn.insertEdge("s", "A", 1);
        fn.insertEdge("s", "D", 4);
        fn.insertEdge("s", "G", 6);
        fn.insertEdge("A", "B", 2);
        fn.insertEdge("A", "E", 2);
        fn.insertEdge("D", "A", 3);
        fn.insertEdge("D", "E", 3);
        fn.insertEdge("G", "D", 2);
        fn.insertEdge("G", "E", 1);
        fn.insertEdge("G", "H", 6);
        fn.insertEdge("B", "C", 2);
        fn.insertEdge("E", "C", 2);
        fn.insertEdge("E", "F", 3);
        fn.insertEdge("E", "I", 3);
        fn.insertEdge("H", "E", 2);
        fn.insertEdge("H", "I", 6);
        fn.insertEdge("C", "t", 4);
        fn.insertEdge("F", "C", 1);
        fn.insertEdge("F", "t", 3);
        fn.insertEdge("I", "F", 1);
        fn.insertEdge("I", "t", 4);
        System.out.println(fn.maxFlow("s", "t"));

//        flowNetwork f = new flowNetwork();
//        f.insertEdge("s", "o",3);
//        f.insertEdge("s","p",3);
//        f.insertEdge("o","p",2);
//        f.insertEdge("o","q",3);
//        f.insertEdge("p","r",2);
//        f.insertEdge("r","t",3);
//        f.insertEdge("q","r",4);
//        f.insertEdge("q","t",2);
//        System.out.println(f.maxFlow("s","t"));
    }
}

class flowNetwork {
    private static class edge {

        public String source;
        public String sink;
        public int capacity;
        public int flow;
        public boolean rev;

        public edge(String source, String sink, int capacity,boolean rev) {
            this.source = source;
            this.sink = sink;
            this.capacity = capacity;
            this.flow = 0;
            this.rev=rev;
        }
        public String toString(){
            return source+"->"+sink+" "+capacity+"  "+flow+"  ";
        }
    }

    public flowNetwork() {
        edges = new HashMap<>();
        edgesR = new HashMap<>();
    }

    public void insertEdge(String source, String sink, int capacity) {
        if (!edges.containsKey(source)) edges.put(source, new LinkedList<>());
        if (!edges.containsKey(sink)) edges.put(sink, new LinkedList<>());
        edges.get(source).add(new edge(source, sink, capacity, false));
        edges.get(sink).add(new edge(sink, source, 0, true));
        if (!edgesR.containsKey(source)) edgesR.put(source, new LinkedList<>());
        if (!edgesR.containsKey(sink)) edgesR.put(sink, new LinkedList<>());
        edgesR.get(source).add(new edge(source, sink, capacity, false));
        edgesR.get(sink).add(new edge(sink, source, 0, true));
    }

    public int maxFlow(String source, String sink) {
        LinkedList<edge> path = findPath(source, sink, new LinkedList<>());

        while (!path.isEmpty()) {
            int flow = Integer.MAX_VALUE;
            for (edge e : path) if (e.capacity - e.flow < flow) flow = e.capacity - e.flow;
            for (edge e : path) {
                e.flow += flow;
                for (edge ed : edges.get(e.sink)) if (Objects.equals(ed.sink, e.source)&&ed.rev)
                    ed.flow -= flow;

            }
            path = findPath(source, sink, new LinkedList<>());
//            System.out.println();
//            for(edge e:path)System.out.print(e);
        }
        int sum = 0;
        for (edge e : edges.get(source)) {

            sum += e.flow;
        }
        reset();
        return sum;
    }

    private final Map<String, LinkedList<edge>> edges;
    private final Map<String, LinkedList<edge>> edgesR;

    private LinkedList<edge> findPath(String source, String sink, LinkedList<edge> path) {
        if (Objects.equals(sink, source)) return path;
        for (edge e : edges.get(source)) {
            int residual = e.capacity - e.flow;

            if (residual > 0 &&!path.contains(e)) {

                path.add(e);
                LinkedList<edge> result = findPath(e.sink, sink, path);
                if (!result.isEmpty()) return result;
            }

        }
        return new LinkedList<>();
    }

    private void reset() {
        for (Map.Entry<String, LinkedList<edge>> e : edgesR.entrySet()) {
            String source = e.getKey();
            LinkedList<edge> edge = e.getValue();
            edges.put(source, new LinkedList<>(edge));
        }
    }

}