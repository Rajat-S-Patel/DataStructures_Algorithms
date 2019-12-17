/**
 * LongestCommonSubsequence
 */
 /**
     * this method uses dynamic programming concept.
     * The main logic behind this is If we have 2 string and we have to find the longest common subsequence
     * then we can find using dynamic programming
     * The optimal substructure for this problem is : 
     * 1) if both strings have same last element then LCS(s1[1 --- m],s2[1 ---n])=LCS(s1[1 --- m-1],s2[1 ---n-1])+1
     * 2) if both strings have different last element then
     *      LCS(s1[1 --- m],s2[1 ---n])=max(LCS(s1[1 --- m-1],s2[1 ---n]),LCS(s1[1 --- m],s2[1 ---n-1]))
     *  because for example :
     *  s1=ABCBDAB s2= BDCABA
     *  if LCS ends with B then it can't end with A and we can remove A and vice versa
     * 
     * Here I used Bottom Up approach as well as Top down approach
     * 
     * */
import java.util.*;
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String s1=sc.nextLine();
        String s2=sc.nextLine();

        System.out.println("Number of Longest Common Subsequences Bottom up approach :  ");
        System.out.println(LCSBottomUp(s1,s2));
        int dp[][]=new int[s1.length()+1][s2.length()+1];
        System.out.println("Number of Longest Common Subsequences Top down approach :  ");
        System.out.println(LCSTopDown(s1, s2, dp,s1.length(),s2.length()));
    }
   
    private static int LCSBottomUp(String s1,String s2){
        int n=s1.length();
        int m=s2.length();
        int dp[][]=new int[n+1][m+1];

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j]=Integer.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
    private static int LCSTopDown(String s1,String s2,int dp[][],int l1,int l2){
        if(l1==0||l2==0){
            return 0;
        }
        if(dp[l1][l2]==0){
            if(s1.charAt(l1-1)==s2.charAt(l2-1)){
                dp[l1][l2]=LCSTopDown(s1, s2, dp, l1-1, l2-1)+1;
            }
            else{
                dp[l1][l2]=Integer.max(LCSTopDown(s1, s2, dp, l1-1, l2), LCSTopDown(s1, s2, dp, l1, l2-1));
            }
        }
        return dp[l1][l2];
        
    }   
}
