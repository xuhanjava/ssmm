package com.google.ssmm.algorithm.leetcode.tiku;

public class n300 {
    //status[j] = status[j-1] +1
    public static int lengthOfLIS(int arr[]) {
        if (arr.length == 0) {
            return 0;
        }
        int[] status = new int[arr.length + 1];
        for (int i = 0; i < status.length; i++) {
            status[i] = 1;
        }
        //status[i] = status[]
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (status[j] > 0 && arr[j] < arr[i]) {
                    status[i] = Math.max(status[j] + 1, status[i]);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(status[i], max);
        }
        return max;
    }

    public static int lengthOfLIS2(int[] nums) {
        int[]dp = new int[nums.length];
        for(int i =0;i<nums.length;i++){
            dp[i] = 1;
        }
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }

            }
        }
        int max = dp[0];
        for(int i=1;i<nums.length;i++){
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {0,1,0,3,2,3};
        System.out.println(lengthOfLIS2(array));
    }
}
