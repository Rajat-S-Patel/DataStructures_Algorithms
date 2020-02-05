def mindel(s,i,j,dp):
    if dp[i][j] is None:
        if i>=j:
           dp[i][j]=0
        elif s[i]==s[j]:
            ans= mindel(s,i+1,j-1,dp)
            dp[i][j]=ans
            
        else:
            ans= 1 + min(mindel(s,i+1,j,dp),mindel(s,i,j-1,dp))
            dp[i][j]=ans
        
    return dp[i][j]


if __name__ == "__main__":
    s=input()
  
    dp=[[None for x in range(len(s))] for y in range(len(s))] 
    print(a)
    print(mindel(s,0,len(s)-1,dp))
    
