import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q7_1 {
    public static void main(String[] args) {
        graph g =new graph(7);
        g.insertEdge(1,2);
        g.insertEdge(1,3);
        g.insertEdge(1,4);
        g.insertEdge(2,4);
        g.insertEdge(2,5);
        g.insertEdge(3,6);
        g.insertEdge(4,3);
        g.insertEdge(4,6);
        g.insertEdge(4,7);
        g.insertEdge(5,4);
        g.insertEdge(5,7);
        g.insertEdge(7,6);
        g.topSortSimple();
        g.topSort();
        g.unweighted(1);
    }
}



class graph {
    private static class Vertex {
        public Vertex(int n) {
            nextVertex = new ArrayList<>();
            indegree = 0;
            number = n;
            isFind = false;
        }
        public Vertex(Vertex v){
            nextVertex = new ArrayList<>(v.nextVertex);
            dis=Integer.MAX_VALUE;
            indegree = v.indegree;
            number = v.number;
            isFind = false;
        }
        public ArrayList<Integer> getNextVertex() {
            return nextVertex;
        }
public int dis;
        public int number;
        public int topNum;
        public int indegree;
        public boolean isFind;
        public ArrayList<Integer> nextVertex;
    }

    public graph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        this.vertexs = new Vertex[vertexNumber+1];

        for(int i=1;i<vertexNumber+1;i++){
            this.vertexs[i]= new Vertex(i);
        }
    }


    public graph(graph g) {
        this.vertexNumber = g.vertexNumber;
        this.vertexs=new Vertex[this.vertexNumber+1];
        for(int i=1;i<vertexNumber+1;i++){
            this.vertexs[i]= new Vertex(g.vertexs[i]);
        }

    }

    public void insertEdge(int x, int y) {
        vertexs[x].nextVertex.add(y);
        vertexs[y].indegree++;

    }

    public void topSortSimple() {
        graph g = new graph(this);
        for (int counter = 0; counter < vertexNumber; counter++) {
            Vertex v = g.findNewVertexOfIndegreeZero();
            if (v == null) {
                System.out.println("图中有回路，无法进行拓扑排序！");
                break;
            }
            v.topNum=counter;
            System.out.print(v.number + "->");

            for(Integer i :v.nextVertex){
                g.vertexs[i].indegree--;
            }
        }
        System.out.println();
    }

    public void topSort() {
        graph g = new graph(this);
        Queue<Vertex> q = new LinkedList<>();
        int counter = 0;
        for (int i =1;i<vertexNumber+1;i++)
            if (g.vertexs[i].indegree == 0) {
                g.vertexs[i].isFind = true;
                q.add(g.vertexs[i]);
            }

        while (!q.isEmpty()) {
            Vertex v = q.remove();
            v.topNum = ++counter;
            System.out.print(v.number + "->");
            for(Integer i :v.nextVertex){
                g.vertexs[i].indegree--;
            }
            for (int i=1;i<vertexNumber+1;i++) {
                if (g.vertexs[i].indegree == 0 && !g.vertexs[i].isFind) {
                    g.vertexs[i].isFind = true;
                    q.add(g.vertexs[i]);
                }
            }
        }
        System.out.println();
        if (counter != vertexNumber) System.out.println("图中有回路，无法进行拓扑排序！");


    }

    public void unweighted(int number){
        graph g = new graph(this);
        Queue<Vertex> q=new LinkedList<>();
        q.add(g.vertexs[number]);
        g.vertexs[number].dis=0;

        while(!q.isEmpty()){
            Vertex v=q.remove();
            for(Integer i :v.nextVertex){
                if(!g.vertexs[i].isFind){
                    g.vertexs[i].dis=v.dis+1;
                    System.out.println("到点"+number+"距离为"+g.vertexs[i].dis+"的点为："+i);
                    g.vertexs[i].isFind=true;
                    q.add(g.vertexs[i]);

                }
            }

        }
    }

    private final int vertexNumber;
    private final Vertex[] vertexs;

    private Vertex findNewVertexOfIndegreeZero() {
       for(int i=1;i<vertexNumber+1;i++){

          if (vertexs[i].indegree == 0 && !vertexs[i].isFind) {
                vertexs[i].isFind = true;
                return vertexs[i];
            }
        }
        return null;
    }
}

