/**
 * PrimsMST
 */
import java.util.*;
public class PrimsMST {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();
        List<Pair> [] adjList=new ArrayList[v];
        for(int i=0;i<v;i++){
            adjList[i]=new ArrayList<>();
        }
        for(int i=0;i<e;i++){
            int src=sc.nextInt();
            int des=sc.nextInt();
            int w=sc.nextInt();
            adjList[src-1].add(new Pair(des-1,w));
            adjList[des-1].add(new Pair(src-1,w));
        }
        System.out.println(findMinCost(adjList,v,e));
        
    }

    public static int findMinCost(List<Pair> adjList[],int n,int e) {
       PriorityQueue<Pair> q=new PriorityQueue<>();
       boolean visited[]=new boolean[n];
       q.add(new Pair(0,0));
       int mincost=0;
       while(!q.isEmpty()){
           Pair p=q.poll();
           if(!visited[p.des]){
               mincost+=p.weight;
               visited[p.des]=true;
               for(Pair x:adjList[p.des]){
                 if(!visited[x.des]){
                     q.add(x);
                 }
               }
           }
       }
       return mincost;
    }

    

}
class Pair implements Comparable<Pair>{
    int des,weight;

    public Pair(int des, int weight) {
        
        this.des=des;
        this.weight = weight;
    }
    @Override
    public int compareTo(Pair o) {
        
        return this.weight-o.weight;
    }
    
    
}    