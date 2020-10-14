package com.google.ssmm.algorithm.leetcode.tiku;

public class Five {
    //5. 最长回文子串
    //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //
    //示例 1：
    //
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。
    //示例 2：
    //
    //输入: "cbbd"
    //输出: "bb"



    //todo 写的cbbd就不满足
    public static String longestPalindrome(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        String maxString = "";
        for(int i=0;i<chars.length;i++){
            int left = i-1,right = i+1;
            while(left >= 0 && right < length){
                if (chars[left] != chars[right]){
                    break;
                }
                left--;right++;
            }
            left++;right--;
            if (maxString.length()<= right-left){
                maxString = s.substring(left,right+1);
            }
        }
        return maxString;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaaa"));
        System.out.println(longestPalindrome1("aaaa"));
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

    //babad
    //这个想法是没错，错的是每次开始的地方不对，aaaa这个例子就不满足，然后想到了不是从下标是0开始的，而是从长度是1，2，3……这样开始的，所以就是上述的逻辑
    public static String longestPalindrome1(String s) {
        int n = s.length();
        char[] cArray = s.toCharArray();
        boolean [][]status = new boolean[n][n];
        for(int i=0;i<n;i++){
            status[i][i] = true;
        }
        for(int i =0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(j-i== 1){
                   if(cArray[i] == cArray[j]){
                       status[i][j] = true;
                   }
                }else if(status[i+1][j-1] && cArray[i] == cArray[j]){
                    status[i][j] = true;
                }
            }
        }
        int maxSize = -1;
        String maxString = "";
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(status[i][j]){
                    int diff = j-i;
                    if (diff > maxSize){
                        maxString = s.substring(i,j+1);
                        maxSize = diff;
                    }
                }
            }
        }
        return maxString;
    }
}
