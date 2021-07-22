package com.google.ssmm.algorithm.leetcode.tiku;

public class n168 {
    public static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n >0) {
           n--;
           sb.append((char)('A'+n %26));
           n /=26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(703));
    }
}
