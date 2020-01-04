/**
 * UnreachableNodes
 */
import java.util.*;
public class UnreachableNodes {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        List<Integer>edges []=new ArrayList[n];
        for(int i=0;i<n;i++){
         edges[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int src=sc.nextInt();
            int des=sc.nextInt();
            edges[src-1].add(des-1);
            edges[des-1].add(src-1);
        }
        int start=sc.nextInt();
        System.out.println(printAns(edges,n,m,start));
    }
    public static int printAns(List<Integer> edges[],int n,int m,int start) {
        Stack<Integer> stack=new Stack<Integer>();
        stack.push(start);
        boolean visited[]=new boolean[n];
        visited[start]=true;
        while(!stack.empty()){
            int v=stack.pop();
            for(int x: edges[v]){
                if(!visited[x]){
                    stack.push(x);
                    visited[x]=true;
                }
            }
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                count++;
            }
        }
        return count;
    }
}