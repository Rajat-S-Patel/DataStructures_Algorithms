
import java.util.*;

/**
 * LongestIncreasingSubsequence
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        int dp[]=new int[n];
        System.out.println("Number of LIS : "+totalLis(a,n,dp));
        System.out.println("LIS : ");
        printLIS(a,n);
        
    }
    private static void printLIS(int a[],int n){
        // List<List<Integer>> LIS = new ArrayList<>();
        List<List<Integer>> LIS=new ArrayList<>();
        for(int i=0;i<n;i++){
           LIS.add(new ArrayList<>()); 
        }

        LIS.get(0).add(a[0]);

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(a[i]>a[j]&&(LIS.get(i).size()<LIS.get(j).size())){
                    LIS.set(i,new ArrayList<>(LIS.get(j)));
                    
                }
            }
            LIS.get(i).add(a[i]);
        }

        int j=0;
        for(int i=0;i<n;i++){
            if(LIS.get(i).size()>LIS.get(j).size()){
                j=i;
            }
        }
        
         System.out.println(LIS.get(j));
    }
    private static int totalLis(int a[],int n,int dp[]){
        dp[0]=1;    // dp[i] stores length of LIS that ends with A[i]
                    //  length of LIS ending with A[0] is 1
                    // LIS ending with A[i] is Maximum length of LIS[0 ---- i-1] + 1.
        
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(a[i]>a[j]&&dp[i]<dp[j]){
                    dp[i]=dp[j];
                }
            }
            dp[i]++;
        }

        return Arrays.stream(dp).max().getAsInt();  // returns maximum from dp[] array
    }
}