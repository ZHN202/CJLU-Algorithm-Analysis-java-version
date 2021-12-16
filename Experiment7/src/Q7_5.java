import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class Q7_5 {
    public static void main(String[] args) {
        grap g = new grap();
        g.insertEdge("A", "C");
        g.insertEdge("A", "D");
        g.insertEdge("C", "D");
        g.insertEdge("C", "B");
        g.insertEdge("C", "F");
        g.insertEdge("B", "E");
        g.insertEdge("F", "E");
        g.insertEdge("F", "G");
        g.insertEdge("E", "H");
        g.insertEdge("E", "I");
        g.insertEdge("H", "J");
        g.insertEdge("I", "K");
        g.insertEdge("J", "K");
        g.findArt();
        g.findBic();
    }
}

class grap {
    private static class Vertex {
        public LinkedList<String> adj;
        public String name;
        public Vertex parent;
        public boolean visited;
        public int num;
        public int low;

        public Vertex(String name) {
            this.name = name;
            this.visited = false;
            this.adj = new LinkedList<>();
        }

        public Vertex(grap.Vertex v) {
            adj = new LinkedList<>(v.adj);
            visited = false;
            num = v.num;
        }

        public String toString() {
            return name + "  num:" + num + "  low:" + low + "  ";
        }
    }

    public grap() {
        this.vertexs = new LinkedList<>();
    }

    public grap(grap g) {
    }

    public void findBic() {
        findBic(vertexs.get(0));

        for (LinkedList<Vertex> vs : biconnected) {
            for (Vertex v : vs) System.out.print(v);
            System.out.println();
        }
    }

    public void findArt() {
        findArt(vertexs.get(0));
        for (Vertex v : vertexs)
            System.out.println(v);
    }

    public void insertEdge(String x, String y) {
        boolean findx = false;
        boolean findy = false;
        for (Vertex v : vertexs)
            if (Objects.equals(v.name, x)) {
                v.adj.add(y);
                findx = true;
            } else if (Objects.equals(v.name, y)) {
                v.adj.add(x);
                findy = true;
            }
        if (!findx) {
            Vertex v = new Vertex(x);
            v.adj.add(y);
            vertexs.add(v);
        }
        if (!findy) {
            Vertex v = new Vertex(y);
            v.adj.add(x);
            vertexs.add(v);
        }
    }

    private LinkedList<Vertex> vertexs;
    private int counter = 1;
    private final LinkedList<LinkedList<Vertex>> biconnected = new LinkedList<>();
    private final Stack<Vertex> Biconnect = new Stack<>();

    private void findArt(Vertex v) {
        v.visited = true;
        v.low = v.num = counter++;
        for (String n : v.adj) {
            for (Vertex ve : vertexs)
                if (Objects.equals(ve.name, n)) {
                    if (!ve.visited) {
                        ve.parent = v;
                        findArt(ve);
                        if (ve.low >= v.num && v.num != 1)
                            System.out.println(v + "is an articulation point");
                        v.low = Math.min(v.low, ve.low);
                    }
                    if (v.parent != ve)
                        v.low = Math.min(v.low, ve.num);
                }
        }
    }

    private void findBic(Vertex v) {
        v.visited = true;
        v.low = v.num = counter++;
        for (String n : v.adj) {
            for (Vertex ve : vertexs)
                if (Objects.equals(ve.name, n)) {
                    if (!ve.visited) {
                        ve.parent = v;
                        findBic(ve);
                        Biconnect.push(ve);
                        if (ve.low >= v.num && v.num != 1) {
                            LinkedList<Vertex> l = new LinkedList<>();
                            while (!Biconnect.empty()) l.add(Biconnect.pop());
                            biconnected.add(l);
                            System.out.println(v + "is an articulation point");
                        }

                        v.low = Math.min(v.low, ve.low);
                    }
                    if (v.parent != ve)
                        v.low = Math.min(v.low, ve.num);
                }
        }
    }
}
