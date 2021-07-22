package com.google.ssmm.algorithm.leetcode.tiku;

public class n516 {
    public int longestPalindromeSubseq(String s) {
        if(s == null){
            return 0;
        }
        int len = s.length();
        if(len == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int[][] dp = new int[len][len];
        for(int i=0;i<len;i++){
            dp[i][i] = 1;
        }
        for(int i=len-2;i>=0;i--){
            for(int j = i+1;j<=len-1;j++){
                if(chars[i] == chars[j]){
                    if(j-i == 1){
                        dp[i][j] = 2;
                    }else{
                        dp[i][j] = dp[i+1][j-1] +2;
                    }
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}
