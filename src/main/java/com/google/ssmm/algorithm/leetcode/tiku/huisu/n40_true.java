package com.google.ssmm.algorithm.leetcode.tiku.huisu;

import java.util.*;

public class n40_true {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> innerList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int item : candidates) {
            if (map.get(item) == null) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }
        Arrays.sort(candidates);
        select(candidates, 0, target, innerList, result, map);
        return result;
    }

    public static void select(int[] candidates, int begin, int target, List<Integer> innerList, List<List<Integer>> result, Map<Integer, Integer> map) {
        if(target == 0){
            result.add(new ArrayList<>(innerList));
            return;
        }
        if(begin >= candidates.length){
            return;
        }
        Integer iCount = map.get(candidates[begin]);
        for (int j = 0; j <= iCount; j++) {
            if (candidates[begin] * j <= target) {
                for(int c = 0;c<j;c++){
                    innerList.add(candidates[begin]);

                }
                //放入对应j次
                select(candidates, begin + iCount, target - (candidates[begin] * j), innerList, result, map);
                for(int c = 0;c<j;c++){
                    innerList.remove(innerList.size()-1);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] candidates = new int[]{1,1,2,3};
        List<List<Integer>> lists = combinationSum2(candidates, 3);
        System.out.println(lists);
    }
}
