package com.google.ssmm.algorithm.leetcode.tiku;

public class n91 {
    static int count = 0;
    public static int numDecodings(String s) {
        put(s, 0);
        return count;
    }

    public static void put(String s, int index) {
        if (index >= s.length()) {
            count++;
            return;
        }
        if (s.charAt(index) ==48) {
            return;
        }
        if (index < s.length() - 1 ) {
            int temp = Integer.parseInt(s.substring(index, index + 2));
            if(temp >0 && temp <27){
                put(s,index +2);
            }
        }

        put(s,index+1);
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("226"));
    }
}
