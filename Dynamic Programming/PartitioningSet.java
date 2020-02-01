/**
 * PartitioningSet
 */
/*
Given a set of n elements, find out number of ways for partioning set in all possible ways
for example if n=2 set s={1,2} then number of ways are 2 because set can be 
partrioned into {1},{2} and {1,2}
*/

/*
recursive relation for the problem can be stated as :
    s(n+1,k)= k*s(n,k) + s(n,k-1)
    where s(n,k) is a set of n elements which can be partioned into 
    k distinct sets.

    this recursive relation can be obtained from :
    1). adding (n+1)th element to every k distinct sets (k*s(n,k))
    2). (n+1)th element added as a single element set to exisiting partitions (s(n,k-1))

*/
import java.util.*;
public class PartitioningSet {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int dp[][]=new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            dp[i][i]=1;
            dp[i][1]=1;
        }

        int ans=0;
        for(int i=1;i<=n;i++)
        {
            ans+=getNumberOfPartitionsDP(n,i,dp);
        }
          
        System.out.println(ans);
    }
    public static int getNumberOfPartitionsDP(int n,int k,int [][] dp){
        if(dp[n][k]!=0)
            return dp[n][k];
        
            dp[n][k]= k*getNumberOfPartitionsDP(n-1, k,dp)+getNumberOfPartitionsDP(n-1, k-1,dp);
            return dp[n][k];
    }
    public static int getNumberOfPartitions(int n,int k){
        
        if(n==k)
            return  1;
        if(k==1)
            return 1;
        if(n==1)
            return 1;
        if(n==0)
            return 0;
        
        return k*getNumberOfPartitions(n-1, k)+getNumberOfPartitions(n-1, k-1);
        
    }
}