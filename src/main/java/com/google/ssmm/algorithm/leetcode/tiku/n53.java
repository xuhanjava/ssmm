package com.google.ssmm.algorithm.leetcode.tiku;

public class n53 {
    //[-2,1,-3,4,-1,2,1,-5,4]
    public static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = maxSubArray1(array);
        System.out.println(i);
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = nums[0];
        int result = nums[0];
        //找到最大的，和所有都>=0的
        boolean[] boolResult = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                boolResult[i] = true;
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        result = max;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (temp > 0 && temp > result) {
                    result = temp;
                }
                temp = 0;
                continue;
            }
            temp += nums[i];
        }
        return result;
    }
}
