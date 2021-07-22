package com.google.ssmm.algorithm.leetcode.tiku;

public class n152 {
    // 11 2 -1 3 -2 0 -2 2 1
    public static int maxProduct(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int [][] dp = new int[2][nums.length];//0:最大，1：最小
        dp[0][0] = nums[0];
        dp[1][0] = nums[0];
        for (int i=1;i<nums.length;i++){
            if(nums[i] >0){
                if(dp[0][i-1]>0){
                    dp[0][i] = dp[0][i-1] * nums[i];
                    if(dp[1][i-1] >0){
                        dp[1][i] = nums[i];
                    }else if (dp[1][i-1] == 0){
                        dp[1][i] = 0;
                    }else {
                        dp[1][i] = dp[1][i-1] * nums[i];
                    }
                }else if(dp[0][i-1] == 0){
                    dp[0][i] = nums[i];
                    if (dp[1][i-1] == 0){
                        dp[1][i] = 0;
                    }else {
                        dp[1][i] = dp[1][i-1] * nums[i];
                    }
                }
                else{
                    dp[1][i] = dp[1][i-1] * nums[i];
                    dp[0][i] = nums[i];
                }

            }else if (nums[i] == 0){
                dp[0][i] = dp[1][i] = 0;
            }else {
                if(dp[1][i-1] <0){
                    dp[0][i] = dp[1][i-1] * nums[i];
                    if(dp[0][i-1] >0){
                        dp[1][i] = dp[0][i-1] * nums[i];
                    }else if (dp[0][i-1] ==0){
                        dp[1][i] = nums[i];
                    }else {
                        // -1 -2 -3 -4
                        dp[1][i]= nums[i];
                    }
                }else if(dp[1][i-1] == 0){
                    dp[0][i]= 0;
                    if(dp[0][i-1] == 0){
                        dp[1][i] = nums[i];
                    }else{
                        dp[1][i] = nums[i] * dp[0][i-1];
                    }
                }else{
                    dp[0][i] = nums[i];
                    dp[1][i] = nums[i] * dp[0][i-1];
                }
            }
        }
        int max= dp[0][0];
        for(int i=1;i<nums.length;i++){
            max =Math.max(max,dp[0][i]);
        }

        return max;
    }


    class Solution {
        public int maxProduct(int[] nums) {
            int maxF = nums[0], minF = nums[0], ans = nums[0];
            int length = nums.length;
            for (int i = 1; i < length; ++i) {
                int mx = maxF, mn = minF;
                //防止前面结果是0的情况
                maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
                minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
                ans = Math.max(maxF, ans);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] array = {2,-1,3,-1};
        maxProduct(array);
    }
}
