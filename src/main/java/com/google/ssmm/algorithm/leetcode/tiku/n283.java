package com.google.ssmm.algorithm.leetcode.tiku;

public class n283 {
    public static void moveZeroes(int[] nums) {
        int index=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        while(index<nums.length){
            nums[index++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,3,0,3,0,1};
        moveZeroes(nums);
        System.out.println(1);
    }
}
