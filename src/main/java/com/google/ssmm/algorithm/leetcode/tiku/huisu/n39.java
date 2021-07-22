package com.google.ssmm.algorithm.leetcode.tiku.huisu;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class n39 {
    //candidates = [2,3,6,7], target = 7
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0){
            return Lists.newArrayList();
        }
        List<Integer> innerList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        select(candidates,0,target,innerList,result);
        return result;
    }

    public static void select(int[] candidates,int i,int target,List<Integer> innerList,List<List<Integer>> result){
        if(i >= candidates.length){
            return;
        }
        if (target == 0){
            result.add(new ArrayList<>(innerList));  //这里直接result.add(innnerList) //有啥区别
            return;
        }
        for(int begin = i;begin < candidates.length;begin++){
            if(target >= candidates[begin]){
                innerList.add(candidates[begin]);
                select(candidates,begin,target-candidates[begin],innerList,result);
                innerList.remove(innerList.size()-1);
            }
        }
        return;
    }

    public static void main(String[] args) {
        int [] canns = new int[]{2,3,6,7};
        List<List<Integer>> lists = combinationSum(canns, 7);
        System.out.println(lists);
    }
}

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}


