package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class n46 {
    public static List<List<Integer>> permute(int[] nums) {
        //len = 0
        Set<Integer> set = new HashSet<>();
        List<Integer> innerList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        select(nums.length,nums,innerList,result,set);
        return result;
    }

    private static void select(int length,int[] nums, List<Integer> innerList, List<List<Integer>> result, Set<Integer> set) {
        if(innerList.size() == length){
            result.add(new ArrayList<>(innerList));
            return;
        }
        for(int i = 0;i<length;i++){
            if(!set.contains(nums[i])){
                innerList.add(nums[i]);
                set.add(nums[i]);
                select(length,nums,innerList,result,set);
                innerList.remove(innerList.size() - 1);
                set.remove(nums[i]);
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{1,2,3};
        List<List<Integer>> permute = permute(nums);
        System.out.println(permute);
    }

}
