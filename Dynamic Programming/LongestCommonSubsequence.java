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

        System.out.println("Length of Longest Common Subsequences Bottom up approach :  ");
        System.out.println(LCSBottomUp(s1,s2));
        int dp[][]=new int[s1.length()+1][s2.length()+1];
        System.out.println("Length of Longest Common Subsequences Top down approach :  ");
        System.out.println(LCSTopDown(s1, s2, dp,s1.length(),s2.length()));

        System.out.println("LCS : "+getLCS(s1,s2,s1.length(),s2.length(),dp));
        System.out.println("All LCS : ");
        List<String> allLcs=getAllLCS(s1, s2, s1.length(), s2.length(), dp);
        for(String s:allLcs){
            System.out.println(s);
        }
    }

    // Returns Single String as an LCS
    private static String getLCS(String s1,String s2,int m,int n,int dp[][]){
        if(m==0||n==0){
            return new String();
        }
        // If both have same last character then append that character in resulting string.
        if(s1.charAt(m-1)==s2.charAt(n-1)){
            return getLCS(s1, s2, m-1, n-1, dp)+ s1.charAt(m-1);    
        }
        // If last length String by dropping (m-1)th character is more than using (n-1)th character,
        //then drop (m-1)th character.
        if(dp[m-1][n]>dp[m][n-1]){
            return getLCS(s1, s2, m-1, n, dp);
        }
        // else drop (n-1)th character in LCS
        else{
            return getLCS(s1, s2, m, n-1, dp);
        }
    }
    // Above method ignores LCS when both string have different last characters and same lcs possibilities.
    // To get All lcs we will include both lcs if they have different characters and still they can make
    // same length lcs i.e when dp[m-1][n]==dp[m][n-1]
    
    private static List<String> getAllLCS(String s1,String s2,int m,int n,int dp[][]){
            if(n==0||m==0){
                // return list with 1 empty string.
                return new ArrayList<>(Collections.nCopies(1,""));
            }
            if(s1.charAt(m-1)==s2.charAt(n-1)){
                List<String> lcs= getAllLCS(s1, s2, m-1, n-1, dp);

                for(int i=0;i<lcs.size();i++){
                    lcs.set(i,lcs.get(i)+s1.charAt(m-1));
                }

                return lcs;
            }
            if(dp[m-1][n]>dp[m][n-1]){
                return getAllLCS(s1, s2, m-1, n, dp);
            }
            if(dp[m][n-1]>dp[m-1][n]){
                return getAllLCS(s1, s2, m, n-1, dp);
            }

            List<String> topLcs=getAllLCS(s1, s2, m-1, n, dp);
            List<String> leftLcs=getAllLCS(s1, s2, m, n-1, dp);

            topLcs.addAll(leftLcs);
            return topLcs;

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
