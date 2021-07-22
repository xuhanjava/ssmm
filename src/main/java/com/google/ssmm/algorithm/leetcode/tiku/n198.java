package com.google.ssmm.algorithm.leetcode.tiku;

public class n198 {
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];

        for (int i=2;i<nums.length;i++){
            for(int j=0;j<i-1;j++){
                dp[i] = Math.max(dp[j] + nums[i],dp[i]);
            }

        }
        int maxV = nums[0];
        for(int i =1;i<nums.length;i++){
            maxV = Math.max(dp[i],maxV);
        }
        return maxV;
    }
}
