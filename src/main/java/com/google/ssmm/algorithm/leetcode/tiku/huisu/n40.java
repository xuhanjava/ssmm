package com.google.ssmm.algorithm.leetcode.tiku.huisu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//错误的例子，没理解其中的意思
public class n40 {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> innerList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        select(candidates, target, innerList, result, 0,set);
        return result;
    }

    static void select(int[] candidates, int target, List<Integer> innerList, List<List<Integer>> result, int begin, Set<Integer> set) {
        if (target == 0  ) { //初始条件
            result.add(new ArrayList<>(innerList));
            return;
        }
        if (begin >= candidates.length) {
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                if(!set.contains(candidates[i])){
                    set.add(candidates[i]);
                    innerList.add(candidates[i]);
                    select(candidates, target - candidates[i], innerList, result, i + 1,set);
                    set.remove(candidates[i]);
                    innerList.remove(innerList.size() - 1);
                    set.remove(candidates[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{1, 2, 7, 6, 1, 5};
        List<List<Integer>> lists = combinationSum2(candidates, 8);
        System.out.println(lists);
    }
}
