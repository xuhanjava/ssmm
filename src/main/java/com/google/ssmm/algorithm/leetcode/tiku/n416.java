package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.Arrays;

public class n416 {
    public static boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        Arrays.sort(nums);
        int half = nums[0];

        int [] sum = new int[nums.length];
        sum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            half+= nums[i];
            sum[i] = nums[i] + sum[i-1];
        }
        if(half % 2 == 1){
            return false;
        }
        for(int i =0;i<nums.length;i++){
            if(sum[i] == half/2){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 1,2,2};
        System.out.println(canPartition(array));
    }
}
