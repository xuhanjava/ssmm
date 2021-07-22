package com.google.ssmm.algorithm.leetcode.tiku;

public class n1143 {
    public static int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        int [][] dp = new int[len1][len2];
        for(int i=0;i<len1;i++){
            for(int j=0;j<len2;j++){
                dp[i][j] = 0;
            }
        }
        for(int i=0;i<len1;i++){
             if(text1.substring(0,i+1).indexOf(text2.charAt(0)) != -1){
                 dp[i][0] = 1;
             }
        }

        for(int j=0;j<len2;j++){
            if(text2.substring(0,j+1).indexOf(text1.charAt(0)) != -1){
                dp[0][j] = 1;
            }
        }

        for(int i=1;i<len1;i++){
            for(int j=1;j<len2;j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],Math.max(dp[i][j-1],dp[i-1][j-1]));
                }
            }
        }
        return dp[len1-1][len2-1];
    }

    public static void main(String[] args) {
        longestCommonSubsequence("abcde","ce");
    }
}
