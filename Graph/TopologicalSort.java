import java.util.*;

/**
 * TopologicalSort
 */
public class TopologicalSort {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        boolean a[][]=new boolean[n][n];

        for (int i = 0; i < m; i++) {
            int src=sc.nextInt();
            int des=sc.nextInt();

            a[src-1][des-1]=true;
            
        }
        int ordering[]=new int[n];
        int i=n-1;
        boolean visited[]=new boolean[n];
        for(int v=0;v<n;v++){
            if(!visited[v])
                i=dfs(v,a,n,m,i,visited,ordering);
        }

        for(int k=0;k<n;k++){
            System.out.print((ordering[k]+1)+" ");
        }
    }

    private static int dfs(int v,boolean[][] a, int n, int m,int i,boolean[] visited,int ordering[]) {
        visited[v]=true;
        for(int j=0;j<n;j++){
            if(a[v][j]){
                if(!visited[j]){
                    i=dfs(j, a, n, m, i, visited, ordering);
                }
            }
        }
        ordering[i]=v;
        return i-1;
    }
}