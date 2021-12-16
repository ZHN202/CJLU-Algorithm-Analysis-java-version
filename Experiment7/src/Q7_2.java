import java.util.*;

public class Q7_2 {
    public static void main(String[] args) {
        gra g=new gra(7);
        g.insertEdge(1,2,2);
        g.insertEdge(1,4,1);
        g.insertEdge(2,4,3);
        g.insertEdge(2,5,10);
        g.insertEdge(3,1,4);
        g.insertEdge(3,6,5);
        g.insertEdge(4,3,2);
        g.insertEdge(4,5,2);
        g.insertEdge(4,6,8);
        g.insertEdge(4,7,4);
        g.insertEdge(5,7,6);
        g.insertEdge(7,6,1);
        g.dijkstra1(1,5);
        g.dijkstra1(4,7);
        g.dijkstra2(1,5);
        g.dijkstra2(4,7);
        g.dijkstra1(1);
        g.dijkstra2(1);
    }
}
class gra{
    private static class Vertex{
        public Map<Integer,Integer> adj;
        public boolean known;
        public int dist;
        public Vertex path;
        public int number;
        public Vertex(int number){
            this.number=number;
            this.dist=Integer.MAX_VALUE;
            this.known=false;
            this.path=null;
            this.adj=new HashMap<>();
        }
        public Vertex(gra.Vertex v){
            adj = new HashMap<>(v.adj);
            dist=Integer.MAX_VALUE;
            known=false;
            number = v.number;
            path=null;
        }
        static Comparator<Vertex> cmp = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                // TODO Auto-generated method stub
                return v1.dist-v2.dist;
            }
        };

    }
    public gra(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        this.vertexs = new gra.Vertex[vertexNumber+1];

        for(int i=1;i<vertexNumber+1;i++){
            this.vertexs[i]= new Vertex(i);
        }
    }
    public gra(gra g) {
        this.vertexNumber = g.vertexNumber;
        this.vertexs=new gra.Vertex[this.vertexNumber+1];
        for(int i=1;i<vertexNumber+1;i++){
            this.vertexs[i]= new Vertex(g.vertexs[i]);
        }

    }
    public void insertEdge(int from, int to,int dist) {
        vertexs[from].adj.put(to,dist);
    }

    //优先队列
    public void dijkstra1(int from,int to){
        gra g=new gra(this);
        g.vertexs[from].dist=0;
        Queue<Vertex> q=new PriorityQueue<>(Vertex.cmp);
        q.add(g.vertexs[from]);
        while (!q.isEmpty()){
            Vertex v = q.remove();
            if(v.known)continue;
            v.known=true;
            for(Map.Entry<Integer,Integer> i : v.adj.entrySet()){
                int n=i.getKey();
                int d=i.getValue();
                if(!g.vertexs[n].known&&d+v.dist<g.vertexs[n].dist){
                    g.vertexs[n].dist=d+v.dist;
                    g.vertexs[n].path=v;
                    q.add(g.vertexs[n]);
                }

            }
        }
        Vertex v = g.vertexs[to];
        System.out.print("path "+from+" to "+to+":"+v.number);
        while(v.path!=null){
            System.out.print(" <- ");
            v=v.path;
            System.out.print(v.number);
        }

        System.out.println(" dist:"+g.vertexs[to].dist);
    }
    //优先队列
    public void dijkstra1(int from){
        gra g=new gra(this);
        g.vertexs[from].dist=0;
        Queue<Vertex> q=new PriorityQueue<>(Vertex.cmp);
        q.add(g.vertexs[from]);
        while (!q.isEmpty()){
            Vertex v = q.remove();
            if(v.known)continue;
            v.known=true;
            for(Map.Entry<Integer,Integer> i : v.adj.entrySet()){
                int n=i.getKey();
                int d=i.getValue();
                if(!g.vertexs[n].known&&d+v.dist<g.vertexs[n].dist){
                    g.vertexs[n].dist=d+v.dist;
                    g.vertexs[n].path=v;
                    q.add(g.vertexs[n]);
                }

            }
        }
        for(int i=1;i<vertexNumber+1;i++) {
            Vertex v = g.vertexs[i];
            System.out.print("path "+from+" to "+i+":"+v.number);
            while (v.path != null) {
                System.out.print(" <- ");
                v = v.path;
                System.out.print(v.number);
            }

            System.out.println(" dist:" + g.vertexs[i].dist);
        }
    }
    //循环比较
    public void dijkstra2(int from,int to){
        gra g=new gra(this);
        g.vertexs[from].dist=0;
        for(int i =1;i<g.vertexNumber+1;i++){
            int x=0,min_dist=Integer.MAX_VALUE;
            for(int j=1;j<g.vertexNumber+1;j++)
                if(!g.vertexs[j].known&&g.vertexs[j].dist<min_dist){
                    min_dist=g.vertexs[j].dist;
                    x=j;
                }
            g.vertexs[x].known=true;
            for(Map.Entry<Integer,Integer> y : g.vertexs[x].adj.entrySet()){
                int n=y.getKey();
                int d=y.getValue();
                if(g.vertexs[n].dist>g.vertexs[x].dist + d)
                    g.vertexs[n].path=g.vertexs[x];
                g.vertexs[n].dist= Math.min(g.vertexs[n].dist, g.vertexs[x].dist + d);
            }

        }
        Vertex v = g.vertexs[to];
        System.out.print("path "+from+" to "+to+":"+v.number);
        while(v.path!=null){
            System.out.print(" <- ");
            v=v.path;
            System.out.print(v.number);
        }

        System.out.println(" dist:"+g.vertexs[to].dist);
    }
    //循环比较
    public void dijkstra2(int from){
        gra g=new gra(this);
        g.vertexs[from].dist=0;
        for(int i =1;i<g.vertexNumber+1;i++){
            int x=0,min_dist=Integer.MAX_VALUE;
            for(int j=1;j<g.vertexNumber+1;j++)
                if(!g.vertexs[j].known&&g.vertexs[j].dist<min_dist){
                    min_dist=g.vertexs[j].dist;
                    x=j;
                }
            g.vertexs[x].known=true;
            for(Map.Entry<Integer,Integer> y : g.vertexs[x].adj.entrySet()){
                int n=y.getKey();
                int d=y.getValue();
                if(g.vertexs[n].dist>g.vertexs[x].dist + d)
                    g.vertexs[n].path=g.vertexs[x];
                g.vertexs[n].dist= Math.min(g.vertexs[n].dist, g.vertexs[x].dist + d);
            }

        }
        for(int i=1;i<vertexNumber+1;i++) {
            Vertex v = g.vertexs[i];
            System.out.print("path "+from+" to "+i+":"+v.number);
            while (v.path != null) {
                System.out.print(" <- ");
                v = v.path;
                System.out.print(v.number);
            }

            System.out.println(" dist:" + g.vertexs[i].dist);
        }
    }
    private Vertex[] vertexs;
    private int vertexNumber;
}
