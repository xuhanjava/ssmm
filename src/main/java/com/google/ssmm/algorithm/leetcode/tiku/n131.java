package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
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
//
public class n131 {
    public static void main(String[] args) {
        List<List<String>> babad = partition("cbbbcc");
        System.out.println(babad);
    }


    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        huisu(s,s.toCharArray(),0,result,tempList);
        return result;
    }

    public static void huisu(String s,char[] array, int begin, List<List<String>> result, List<String> tempList) {
        if(begin >= array.length){
            List<String> tList = new ArrayList<>(tempList);
            result.add(tList);
            return;
        }
        for (int i = 0; begin + i < array.length; i++) {
            if (isHuiWen(array, begin,begin+i)) {
                String tempStr = s.substring(begin,begin+i+1);
                tempList.add(tempStr);
                huisu(s,array,begin+i+1,result,tempList);
                tempList.remove(tempList.size()-1);
            }
        }
        return;

    }

    //动态规划算出一次是不是最优的
    public static boolean isHuiWen(char[] array, int begin, int end) {
        while(begin <= end){
            if( array[begin] != array[end]){
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        String[][] dp = new String[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = "" + charArray[i];
            if (i < len - 1) {
                dp[i + 1][i] = "";
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (charArray[i] == charArray[j] && dp[i + 1][j - 1] != null && dp[i + 1][j - 1].length() == j - i - 1) {
                    dp[i][j] = charArray[i] + dp[i + 1][j - 1] + charArray[j];
                } else {
                    if (dp[i + 1][j] == null && dp[i][j - 1] == null) {
                        continue;
                    }
                    if (dp[i + 1][j] == null) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    if (dp[i][j - 1] == null) {
                        dp[i][j] = dp[i + 1][j];
                    }

                    if (dp[i + 1][j].length() > dp[i][j - 1].length()) {
                        dp[i][j] = dp[i + 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }

        }
        String maxT = "";
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j] == null) {
                    continue;
                }
                if (maxT.length() < dp[i][j].length()) {
                    maxT = dp[i][j];
                }
            }
        }

        return maxT;
    }

    //"aacabdkacaa"
    //"aca"
}
