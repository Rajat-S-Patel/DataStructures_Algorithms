
/**
 * BellmanFord
 */
import java.util.*;

public class BellmanFord {
    public static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        // Edge edge[] = new Edge[e];
        List<List<Edge>> adjList=new ArrayList<>();
        for(int i=0;i<v;i++){
            adjList.add(i,new ArrayList<>());
        } 
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int des = sc.nextInt();
            int weight = sc.nextInt();
            adjList.get(src-1).add(new Edge(src-1, des-1, weight));
        }
        
        bellmanFord(adjList, v, e);
        // System.out.println("Shortest Path " + bellmanFord(edge, v, e));
    }

    public static void bellmanFord(List<List<Edge>> adjList, int v, int e) {
        int[] distance = new int[v];
        int parent[] = new int[v];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = INFINITY;
            parent[i] = -1;
        }
        distance[0] = 0;
        for (int i = 0; i < v - 1; i++) {
            int j=0;
            while(j<adjList.get(i).size()){
                int des=adjList.get(i).get(j).des;
                int weight=adjList.get(i).get(j).weight;
                if(distance[i]+weight<distance[des]){
                    distance[des]=distance[i]+weight;
                }
                j++;
            }
        }
        
       
        for(int i=1;i<v;i++){
            System.out.print(distance[i]+" ");
        }
       
    }
}

class Edge {
    int src, des, weight;

    public Edge(int src, int des, int weight) {
        this.src = src;
        this.des = des;
        this.weight = weight;
    }

}