package com.google.ssmm.algorithm.leetcode.tiku;

public class n72 {
    //abs bs
    // dp[i][j] = dp[i-1][j-1],dp[i-1][j],dp[i][j-1]
    // dp[i][j] = dp[i-1][j-1]+1,
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=1;i<=len1;i++){
            dp[i][0] = i;
        }
        for(int j=1;j<=len2;j++){
            dp[0][j] = j;
        }
        char[] array1 = word1.toCharArray();
        char[] array2 = word2.toCharArray();
        for (int i=1;i <= len1;i++) {
            for (int j=1;j<=len2;j++) {
                if (array1[i-1] == array2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[len1][len2];
    }
}
