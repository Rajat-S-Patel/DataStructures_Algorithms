import java.util.*;
import java.io.*;
/**
 * GoldMine
 */
public class GoldMine {
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
        int a[][]=new int [m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                a[i][j]=sc.nextInt();
            }
        }
        count(a,m,n);
    }

    private static void count(int[][] a, int m, int n) {
        int maxGold[][]=new int[m][n];
        for(int col=n-1;col>=0;col--){
            for(int row=0;row<m;row++){
                int right=(col==n-1)?0:maxGold[row][col+1];
                int up_right=(col==n-1||row==0)?0:maxGold[row-1][col+1];
                int low_right=(col==n-1||row==m-1)?0:maxGold[row+1][col+1];

                maxGold[row][col]=a[row][col]+Integer.max(right,Integer.max(up_right, low_right));
            }
        }
        int max=0;
        for(int i=0;i<m;i++){
            if(maxGold[i][0]>max)
                max=maxGold[i][0];
        }
        System.out.println(max);
    }
}