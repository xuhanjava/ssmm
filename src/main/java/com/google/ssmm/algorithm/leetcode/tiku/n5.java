package com.google.ssmm.algorithm.leetcode.tiku;


public class n5 {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[][] dp = new String[s.length() + 1][s.length() + 1];

        for (int l = 0; l < s.length() ; l++) {
            for (int i = 0; i < s.length()-l; i++) {
                if (l == 0) {
                    dp[i][i + l] = "" + s.charAt(i);
                }else if(l == 1){
                    if(s.charAt(i) == s.charAt(i+l)){
                        dp[i][i+l] = s.substring(i,i+l+1);
                    }
                }else{
                    if(s.charAt(i) == s.charAt(i+l) && dp[i+1][i+l-1] != null){
                        dp[i][i+l] = s.substring(i,i+l+1);
                    }
                }
            }
        }
        String max ="";
        //最后的长度就是最长的，不需要再遍历了，所以写的有问题

        return max;
    }

    class Solution {
        public String longestPalindrome(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            String ans = "";
            for (int l = 0; l < n; ++l) {
                for (int i = 0; i + l < n; ++i) {
                    int j = i + l;
                    if (l == 0) {
                        dp[i][j] = true;
                    } else if (l == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j));
                    } else {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                    }
                    if (dp[i][j] && l + 1 > ans.length()) {
                        ans = s.substring(i, i + l + 1);
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        String abccdaadass = longestPalindrome("abccdaadass");
        System.out.println(abccdaadass);
    }
}
