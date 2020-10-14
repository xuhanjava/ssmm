package com.google.ssmm.algorithm.leetcode.dongtai;

public class LengthOfLIS {
    //输入: [10,9,2,5,3,7,101,18]
    //     * 输出: 4
    //     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] status = new int[nums.length + 1]; //1：index位置，2：值，结果：现在的sum //todo xuhan 初始化
        for (int i=0;i<nums.length;i++){
            status[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <i; j++) {
                if (nums[j] < nums[i]) { // =0
                    status[i] = Math.max(status[j] + 1, status[i]);
                }
            }
        }
        //取最大值
        int count = 0;
        for (int i = 0; i < status.length; i++) {
            count = Math.max(count, status[i]);
        }
        return count;
    }
}
