/*
Given a “2 x n” board and tiles of size “2 x 1”,
count the number of ways to tile the given board using the 2 x 1 tiles.
A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.

for example if n=3 answer is 3
This problem can be solved with recursive approach
1). If we place the first tile vertical than th problem reduced for n=n-1;
2). If we place the first tile horizontal than we must put the next tile horzontal in order
    to reduce the problem to n=n-2;
therefore..
count(n)=count(n-1)+count(n-2);
count(n)=n for n=1 and n=2;
*/

/**
 * Tiling
 */
import java.util.*;
public class Tiling {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        dp[1]=1;
        dp[2]=2;
        System.out.println(countDP(n,dp));  // Recursive Solution Optimized with DP
       // System.out.println(count(n)); Recursive Solution
   }

    private static int countDP(int n, int[] dp) {
        if(dp[n]!=-1)
            return dp[n];
        dp[n]=countDP(n-1,dp)+countDP(n-2,dp);
        return dp[n];
    }

    private static int count(int n) {
        if(n==1||n==2)
            return n;
        return count(n-1)+count(n-2);
    }
}