package com.google.ssmm.hystrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(nums.length);
        for (int i=0;i<nums.length-2;i++){
            int k = nums[i];
            Map<Integer,Integer> map = new HashMap<>(nums.length -2);
            for (int j=i+1;j<nums.length;j++){
                if (map.get(-k-nums[j]) != null){
                    List<Integer> tempList = new ArrayList<>(3);
                    tempList.add(nums[i]);tempList.add(-k-nums[j]);tempList.add(nums[j]);
                    result.add(tempList);
                    continue;
                }
                map.put(nums[j],j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num = {0,-1,1,2,-1};
        List<List<Integer>> lists = threeSum(num);
        System.out.println(lists);
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        System.out.println("______");
        map.forEach((key,value)->{
            System.out.println(key +"-"+value);
        });
    }
}