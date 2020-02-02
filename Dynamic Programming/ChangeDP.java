import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m,int dp[]) {
        //write your code here
        if(m<1){
            return 0;
        }
        if(m==1){
            return 1;
        }
        if(dp[m]==-1){
            int res=Integer.min(getChange(m-1, dp), Integer.min(getChange(m-3, dp),getChange(m-4, dp)));
            
                dp[m]=1+res;
            
            return dp[m];
        }
        else{
            return dp[m];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int dp[]=new int[m+1];
        for(int i=0;i<m+1;i++){
            dp[i]=-1;
        }
        dp[0]=0;
        dp[1]=1;
        System.out.println(getChange(m,dp));

    }
}

