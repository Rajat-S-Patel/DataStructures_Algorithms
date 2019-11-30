import java.util.*;

public class kruskalMST {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int V, E;
        V = sc.nextInt();
        E = sc.nextInt();
        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            int src, des, w;
            src = sc.nextInt();
            des = sc.nextInt();
            w = sc.nextInt();
            edges[i] = addnewEdge(src, des, w);
        }

        Arrays.sort(edges);
        kruskalsAlgo(V, edges);

    }

    public static Edge addnewEdge(int src, int des, int w) {
        Edge n = new Edge(src, des, w);
        return n;
    }

    public static void kruskalsAlgo(int V, Edge edges[]) {
        Edge result[] = new Edge[V];
        for (int i = 0; i < V; i++) {
            result[i] = addnewEdge(0, 0, 0);
        }
        subset subsets[] = new subset[V+1];
        for (int i = 0; i <= V; i++) {
            subsets[i] = new subset(i, 0);
        }

        int e = 0;
        int j = 0;
        while (e < V - 1) {
            Edge next = new Edge(0, 0, 0);
            next = edges[j++];
            int x = findRoot(subsets, next.src);
            int y = findRoot(subsets, next.des);

            if (x != y) {
                result[e++] = next;
                Union(subsets, x, y);
            }
        }

        for(int i=0;i<result.length;i++){
            System.out.println(result[i].src+" ----- "+result[i].des+" weight : "+result[i].weight);
        }

    }

    public static void Union(subset subsets[], int x, int y) {
        int xroot = findRoot(subsets, x);
        int yroot = findRoot(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public static int findRoot(subset subsets[],int src){
        if(subsets[src].parent!=src){
            subsets[src].parent=findRoot(subsets,subsets[src].parent);
        }

        return subsets[src].parent;
    }

}

class Edge implements Comparable<Edge> {
    int src, des, weight;
    Edge next;

    Edge(int src, int des, int weight) {
        this.src = src;
        this.des = des;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge node) {
        return this.weight - node.weight;
    }
}

class subset {
    int parent, rank;

    subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }
}
