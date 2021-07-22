package com.google.ssmm.algorithm.leetcode.tiku;

public class n322 {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount +1];
        if(amount == 0){
            return 0;
        }
        for(int i=0;i<coins.length;i++){
            if(coins[i] <= amount ){
                dp[coins[i]] =1;
            }
        }

        for(int w = 0;w<amount;w++){
            for(int i =0;i<coins.length;i++){
                if(w+coins[i] <= amount && dp[w]>0 && w+coins[i]>0){
                    if(dp[w+coins[i]] == 0){
                        dp[w+coins[i]] = dp[w]+1;
                    }else{
                        dp[w+coins[i]] = Math.min(dp[w+coins[i]],dp[w]+1);

                    }
                }
            }
        }
        return dp[amount] == 0?-1:dp[amount];
    }

    public static void main(String[] args) {
        //[1,2147483647]
        //2
        int [] array = {1,2147483641};
        coinChange(array,2);
    }


}
