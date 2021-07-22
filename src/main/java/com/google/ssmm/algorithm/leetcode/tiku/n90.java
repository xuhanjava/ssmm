package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n90 {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        put(nums,temp,result,0);
        return result;
    }

    //[1,1,2]
    static void put(int[] nums, List<Integer> temp, List<List<Integer>> result, int index) {
        if(index == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        int count = 1;
        for (int i = index;i + 1 < nums.length && nums[i] == nums[i + 1];i++) {
            count ++;
        }
        for(int i=0;i<=count;i++){
            for(int j=0;j<i;j++){
                temp.add(nums[index]);
            }
            put(nums,temp,result,index +count);
            for(int j=0;j<i;j++){
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        List<List<Integer>> lists = subsetsWithDup(nums);
        System.out.println(lists);
    }
}
