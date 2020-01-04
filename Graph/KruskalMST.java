/**
 * KruskalMST
 */
import java.util.*;

public class KruskalMST {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();
        Edges edges[]=new Edges[e];
        
        for(int i=0;i<e;i++){
            int src=sc.nextInt();
            int des=sc.nextInt();
            int  w=sc.nextInt();
            edges[i]=new Edges(src-1, des-1, w);
        }
        System.out.println(kruskalmst(edges,v,e));
        
    }
    public static int kruskalmst(Edges[] edges,int v,int e) {
        Arrays.sort(edges);
        int cost=0,mincost=0;
        int id[]=new int[v];
        for(int i=0;i<v;i++){
            id[i]=i;
        }
        for(int i=0;i<edges.length;i++){
            int x=edges[i].src;
            int y=edges[i].des;
            cost=edges[i].w;
            if(root(x,id)!=root(y,id)){
                mincost+=cost;
                union(x,y,id);
            }
        }
        return mincost;
    }

    public static int root(int x,int id[]) {
        while(id[x]!=x){
            id[x]=id[id[x]];
            x=id[x];
        }
        return x;
    }
    public static void union(int x,int y,int id[]) {
        int p=root(x, id);
        int q=root(y, id);
        id[p]=id[q];
    }
}
class Edges implements Comparable<Edges>{
    int src,des,w;
    
    public Edges(int src, int des, int w) {
        this.src = src;
        this.des = des;
        this.w = w;
    }
@Override
public int compareTo(Edges o) {
    // TODO Auto-generated method stub
    return this.w-o.w;
}


}