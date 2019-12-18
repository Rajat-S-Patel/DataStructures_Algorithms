/**
 * LongestAlternatingSubarray
 */
import java.util.*;
public class LongestAlternatingSubarray {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }

        findLAS(a,n);
    }
    private static void findLAS(int a[],int n){
        List<List<Integer>> dp=new ArrayList<>();
        for(int i=0;i<n;i++){
            dp.add(new ArrayList<>());
        }
        dp.get(0).add(a[0]); // base case,only 1st element is included in LAS when size of array is 1.    
        // dp[i][j] represents LAS upto ith index of a[]
        
        for(int i=1;i<n;i++){
            int prev=dp.get(i-1).get(dp.get(i-1).size()-1);
            if((prev>0&&a[i]<0)||(prev<0&&a[i]>0)){
                dp.add(i,dp.get(i-1));
                dp.get(i).add(a[i]);
            }
            else{
                dp.get(i).add(a[i]);
            }
            
        }
        int j=0;
        for(int i=0;i<n;i++){
            if(dp.get(i).size()>dp.get(j).size()){
                j=i;
            }
        }
        System.out.println(dp.get(j));

    }
}