package com.google.ssmm.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * todo xuhan 不会
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：

 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。
 示例 2：

 输入: "cbbd"
 输出: "bb"

 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        String result = "";
        for(int i = 0;i<chars.length;i++) {
            String temp = get(s,chars,i,i);
            String temp2 = get(s,chars,i,i+1);

            if(temp.length() > temp2.length() && temp.length()>result.length()){
                result = temp;
            }else if(temp.length() <= temp2.length() && temp2.length()>result.length()){
                result = temp2;
            }
        }
        if(result == "" && chars.length != 0){
            return ""+chars[0];
        }
        return result;
    }

    private static String get(String s,char[] chars,int begin,int end){
        boolean result = true;
        while(begin>=0 && end<chars.length){
            int beginBefore = begin,endBefore = end;
            if(chars[begin] != chars[end]){
                if(result){
                    if(chars[beginBefore] == chars[begin]){
                        return s.substring(begin,end);
                    }
                    if(end+1 == chars.length){
                        end = chars.length-2;
                    }
                    return s.substring(begin+1,end+1);
                }
                return s.substring(begin+1,end);
            }
            begin--;end++;
            if(begin <0 || end >=chars.length){
                break;
            }
            if(chars[beginBefore] != chars[begin] || chars[endBefore] != chars[end]){
                result = false;
            }
        }

//        if(begin < 0){
//            if(result && chars[0]){
//                begin = 0;
//            }else{
//                begin = 1;
//            }
//
//        }
//        if(end > chars.length){
//            if(result && chars[end]){
//                end = chars.length ;
//            }else{
//                end = chars.length -1;
//            }
//
//        }
        return s.substring(begin,end);
    }

    public static String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome1("babad"));
        System.out.println(longestPalindrome1("cbbd"));
        System.out.println(longestPalindrome1("ac"));
        System.out.println(longestPalindrome1("a"));
        System.out.println(longestPalindrome1(""));
        System.out.println(longestPalindrome1("babadada")); //"adada"
        System.out.println(longestPalindrome1("bb")); //"adada"
        System.out.println(longestPalindrome1("aaabaaaa"));

        System.out.println("tattarrattat");//"tattarrattat"

    }
}

/*

public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}

链接：https://leetcode-cn.com/problems/two-sum/solution/zui-chang-hui-wen-zi-chuan-by-leetcode/


 */