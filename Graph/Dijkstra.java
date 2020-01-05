
/**
 * Dijkstra
 */
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int edges[][]=new int[v][v];
        //List<List<Pair>> edges=new ArrayList<>();
        
        for(int i=0;i<e;i++){
            int src=sc.nextInt();
            int des=sc.nextInt();
            int w=sc.nextInt();
            edges[src-1][des-1]=w;
            
        }


        dijkstra(edges,v,e);
    }
    public static void dijkstra(int edges[][],int v,int e) {
        PriorityQueue<Integer> q=new PriorityQueue<>();
        boolean visited[]=new boolean[v];
        int distance[]=new int[v];
        q.add(0);
        for(int i=0;i<v;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        distance[0]=0;

        while(!q.isEmpty()){
            int node=q.poll();
            visited[node]=true;

            for(int i=0;i<edges[node].length;i++){
                if(edges[node][i]!=0&&!visited[i]&&(distance[node]+edges[node][i]<=distance[i])){
                    q.add(i);
                    distance[i]=distance[node] + edges[node][i];   
                }
            }
        }
        for(int i=1;i<v;i++){
            System.out.print(distance[i]+" ");
        }
    }

}
class Pair implements Comparable<Pair>{
    int vertex,weight;

    public Pair(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
    @Override
    public int compareTo(Pair o) {
        return this.weight-o.weight;
    }
       
}