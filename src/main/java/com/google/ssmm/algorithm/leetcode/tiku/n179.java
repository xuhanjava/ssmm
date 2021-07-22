package com.google.ssmm.algorithm.leetcode.tiku;

public class n179 {
    public String largestNumber(int[] nums) {
        int[] countArray = new int[10];
        for (int i : nums) {
            while (i != 0) {
                int yushu = i % 10;
                i = i / 10;
                countArray[yushu]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=9;i>=0;i--){
            for(int j=0;j<countArray[i];j++){
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
