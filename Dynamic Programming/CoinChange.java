import java.util.*;
import java.io.*;
/**
 * CoinChange
 */
public class CoinChange {
    static class FastReader 
        { 
            BufferedReader br; 
            StringTokenizer st; 
      
            public FastReader() 
            { 
                br = new BufferedReader(new
                         InputStreamReader(System.in)); 
            } 
      
            String next() 
            { 
                while (st == null || !st.hasMoreElements()) 
                { 
                    try
                    { 
                        st = new StringTokenizer(br.readLine()); 
                    } 
                    catch (IOException  e) 
                    { 
                        e.printStackTrace(); 
                    } 
                } 
                return st.nextToken(); 
            } 
      
            int nextInt() 
            { 
                return Integer.parseInt(next()); 
            } 
      
            long nextLong() 
            { 
                return Long.parseLong(next()); 
            } 
      
            double nextDouble() 
            { 
                return Double.parseDouble(next()); 
            } 
      
            String nextLine() 
            { 
                String str = ""; 
                try
                { 
                    str = br.readLine(); 
                } 
                catch (IOException e) 
                { 
                    e.printStackTrace(); 
                } 
                return str; 
            } 
        } 
    public static void main(String[] args) {
        FastReader sc=new FastReader();
        int m=sc.nextInt();
        int n=sc.nextInt();
        int a[]=new int[m];
        for(int i=0;i<m;i++){
            a[i]=sc.nextInt();
        }
        int dp[][]=new int[m+1][n+1];
        for(int row[]:dp)
        Arrays.fill(row, -1);

        for(int i=0;i<m;i++)
            dp[i][0]=1;
        for(int i=1;i<=n;i++)
            dp[0][i]=0;
        System.out.println(count(a,m,n,dp));
    }

    private static int count(int[] a, int m, int n,int[][] dp) {
        
        if(n==0)
            return 1;
        if(n<0)
            return 0;
        
        if(m<=0&&n>=1)
            return 0;
        if(dp[m][n]!=-1)
            return dp[m][n];
        
        dp[m][n]=count(a,m-1,n,dp)+count(a, m, n-a[m-1],dp);
    
        return dp[m][n];
    }
}