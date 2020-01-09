/**
 * BridgesAndArticulationPoint
 */
import java.util.*;
public class BridgesAndArticulationPoint {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();
        int a[][]=new int[v][v];
        for(int i=0;i<e;i++){
            int src=sc.nextInt();
            int des=sc.nextInt();
            a[src][des]=1;
            a[des][src]=1;
        }
        List<Integer> AP=new ArrayList<>();
        List<Pair> Bridges=new ArrayList<>();
        int disc[]=new int[v];
        int low[]=new int[v];
        boolean visited[]=new boolean[v];
        int parent[]=new int[v];
        for(int i=0;i<v;i++){
            low[i]=Integer.MAX_VALUE;
        }

        getAPandBridges(a,0,v,e,disc,low,visited,parent,AP,Bridges,0);
        System.out.println(AP.size());
        Collections.sort(AP);
        for(int x: AP){
            System.out.print(x+" ");
        }
        System.out.println();
        System.out.println(Bridges.size());
        Collections.sort(Bridges);
        for(int i=0;i<Bridges.size();i++){
            int src=Bridges.get(i).src;
            int des=Bridges.get(i).des;
            if(src<des)
                System.out.println(src+" "+des);
            else
                System.out.println(des+" "+src);
        }
    }
   
    public static void getAPandBridges(int a[][],int current,int v,int e,int disc[],int low[],boolean visited[],int parent[],List AP,List Bridges,int time){
        visited[current]=true;
        disc[current]=low[current]=time+1;
        int child=0;
        for(int i=0;i<v;i++){
            if(a[current][i]>0){
                if(!visited[i]){
                    child++;
                    parent[i]=current;
                    getAPandBridges(a, i, v, e, disc, low, visited, parent, AP, Bridges,time+1);
                    low[current]=Integer.min(low[current],low[i]);
                    if(current==0&&child>1){
                        AP.add(current);
                    }
                    if(current!=0&&low[i]>=disc[current]){
                        AP.add(current);

                    }
                    if(low[i]>disc[current]){
                        if(current>i)
                            Bridges.add(new Pair(i, current));
                        else
                            Bridges.add(new Pair(current, i));
                    }
                  
                }
                else if(parent[current]!=i){
                    low[current]=Integer.min(low[current],disc[i]);
                }
            }
        }


    }
}
class Pair implements Comparable<Pair>{
    int src;int des;
    Pair(int src,int des){
        this.src=src;
        this.des=des;
    }
    @Override
    public int compareTo(Pair o) {
        // TODO Auto-generated method stub
        if(this.src!=o.src)
        return this.src-o.src;
        else
        return this.des-o.des;
    }
}