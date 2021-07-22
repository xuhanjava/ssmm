package com.google.ssmm.algorithm.leetcode.tiku;

public class n55 {
    //输入: [2,4,1,1,4]
    //输出: true

    //输入: [3,2,1,0,4]
    //输出: false

    public static boolean canJump1(int[] nums) {
        //(nums[0] <= 0 & nums.length != 1) 这个判断也忘记考虑了
        if(nums == null  || nums.length == 0 || (nums[0] <= 0 & nums.length != 1)){
            return false;
        }
        int [] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1] -1 ,nums[i]);
            //todo xuhan i != nums.length -1 这个判断忘记考虑了
            if(dp[i] == 0 && i != nums.length -1){
                return false;
            }
        }
        return true;
    }

        //f(n-1) + nums[n-1] >0

    //想法：我第一时间想的是动态规划    f(i) = Max(f(i-1)-1,array[i-1])

    static boolean result = true;
    public static boolean canJump(int[] nums) {
        f(nums,nums.length -1);
        return result;
    }

    //return 0 表示不行
    public static int f(int[]nums,int n){
        if(n == 0){
            return nums[0];
        }
        int temp =   Math.max(f(nums,n-1)-1, nums[n-1]);
        if (temp <=0){
            result = false;
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,0};
        System.out.println(canJump1(nums));
    }

    //public class Solution {
    //    public boolean canJump(int[] nums) {
    //        int n = nums.length;
    //        int rightmost = 0;
    //        for (int i = 0; i < n; ++i) {
    //            if (i <= rightmost) {
    //                rightmost = Math.max(rightmost, i + nums[i]);
    //                if (rightmost >= n - 1) {
    //                    return true;
    //                }
    //            }
    //        }
    //        return false;
    //    }
    //}
    //
}
