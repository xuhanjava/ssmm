package com.google.ssmm.algorithm.leetcode.tiku;

public class n213 {
    public int rob1(int[] nums) {
        int[] dp = new int [nums.length];
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        dp[0] = nums[0];
        dp[1] = nums[1];
        for(int i=1;i<nums.length;i++){
            for(int j = i-2;j>=0;j--){
                dp[i] = Math.max(dp[i],dp[j]+nums[i]);
            }
        }
        int maxV = 0;
        for(int v: dp){
            maxV = Math.max(v,maxV);
        }
        return maxV;
    }
}
