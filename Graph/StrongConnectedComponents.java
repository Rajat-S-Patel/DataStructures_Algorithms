import java.util.*;
/**
 * StrongConnectedComponents
 */
public class StrongConnectedComponents {
    public final static int UNVISITED=-1;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        boolean a[][]=new boolean[n][n];
        for(int i=0;i<m;i++){
            int src=sc.nextInt();
            int des=sc.nextInt();
            a[src-1][des-1]=true;
        }

        findScc(a,n,m);
    }
    public static int id=0,sccCount=0;
    private static void findScc(boolean[][] a, int n, int m) {
        int ids[]=new int[n];
        int low[]=new int[n];
        boolean isOnStack[]=new boolean[n];
       
        Stack<Integer> stack=new Stack<Integer>();
        for(int i=0;i<n;i++)
            ids[i]=UNVISITED;
        for(int i=0;i<n;i++){
            if(ids[i]==UNVISITED){
                dfs(a,i,n,m,ids,low,isOnStack,stack);
            }
        }
        
        System.out.println(sccCount); // Number of Scc
        
    }

    private static void dfs(boolean[][] a,int current, int n, int m, int[] ids, int[] low,
            boolean[] isOnStack, Stack<Integer> stack) {
            stack.push(current);
            isOnStack[current]=true;
            ids[current]=low[current]=id++;
            for(int i=0;i<n;i++){
                if(a[current][i]){
                    if(ids[i]==UNVISITED){
                        dfs(a, i, n, m, ids, low, isOnStack, stack);
                    }
                    if(isOnStack[i]){
                        low[current]=Integer.min(low[current],low[i]);
                    }
                }
                
            }

            if(ids[current]==low[current]){
                int p=0;
                do{ p=stack.pop();
                    isOnStack[p]=false;
                    low[p]=ids[current];
                    
                }while(p!=current);
                sccCount++;
            }
    }
}