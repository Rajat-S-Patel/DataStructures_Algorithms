import java.util.*;

/**
 * DirectedFromUndirected
 * 
Given an connected undirected graph and a vertex in the graph,
construct a directed graph from given graph such that any path
in the directed graph leads to that particular vertex.
 */
public class DirectedFromUndirected {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=7;
        List<Edge> edges=Arrays.asList(Edge.of(1, 2), Edge.of(1, 3),
        Edge.of(2, 4), Edge.of(3, 4),
        Edge.of(3, 5), Edge.of(4, 5),
        Edge.of(4, 6));

        Graph graph=new Graph(edges, n);
        int vertex=1;
        edges=BFS(graph,n,vertex);
      
        Graph diGraph=new Graph(edges, n);
        for(Edge edge:edges){
            System.out.println(edge.src+"-----------"+edge.des);
        }
        for(List list:diGraph.adjList){
            System.out.println(list);
        }
        
    }

    public static List<Edge> BFS(Graph graph,int n,int v){
        List<Edge> edges=new ArrayList<>();
        boolean visited[]=new boolean[n];
        visited[v]=true;
        Queue<Integer> q=new ArrayDeque<>();
        q.add(v);
        while(!q.isEmpty()){
            
            v=q.poll();
            for(int x:graph.adjList.get(v)){
                if(!visited[x]){
                    visited[x]=true;
                    edges.add(Edge.of(x,v));
                    q.add(x);
                }
            }
        }
        return edges;
    }
}
class Edge{
    public int src,des;
    public Edge(int src,int des){
        this.src=src;
        this.des=des;
    }
    public static Edge of(int a,int b) {
        return new Edge(a,b);
    }
}
class Graph{
    List<List<Integer>> adjList=null;

    Graph(List<Edge>edges,int N){
        adjList=new ArrayList<>(N);
        for(int i=0;i<N;i++){
            adjList.add(i, new ArrayList<>());
        }
        
        for(int i=0;i<edges.size();i++){
            int src=edges.get(i).src;
            int des=edges.get(i).des;

            adjList.get(src).add(des);
            adjList.get(des).add(src);
        }
    }
}