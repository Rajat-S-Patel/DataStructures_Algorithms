/**
 * MinDelPalindrome
 * 
 * Given a String, Find minimum number of deletions required to convert given string to palindrome
 * This can be done by recursion
 * we can start by comparing 1st and last letter, if they are equal we can recur to the remaining string,
 * leaving the 1st and last letter.
 * If 1st and last letter are not equal then we can take minimum of (deletion required for string after
 * removing 1st character or string after removing last character) plus 1.
 * By using memoization we can reduce the time taken because we have overlapping subproblems and Optimal
 * substructure in the problem.
 */
import java.util.*;
public class MinDelPalindrome {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        //int n=sc.nextInt();
        String s=sc.nextLine();
        int dp[][]=new int[s.length()][s.length()];

        for(int i=0;i<s.length();i++){
            for(int j=0;j<s.length();j++){
                dp[i][j]=-1;
            }
        }
        
        System.out.println("minimum deletion : "+minDel(s,dp,0,s.length()-1));
    }
    private static int minDel(String s,int dp[][],int i,int j){
        if(i>=j){
            return 0;
        }

        if(dp[i][j]!=0){
            if(s.charAt(i)==s.charAt(j)){
                dp[i][j]=minDel(s, dp, i+1, j-1);
            }
            else{
                dp[i][j]=1+Integer.min(minDel(s,dp,i+1,j), minDel(s, dp, i, j-1));
            }
        }
        return dp[i][j];

    }
}