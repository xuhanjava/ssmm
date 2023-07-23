package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;
public class n39true {

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
        //int i = idx;
        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                combine.add(candidates[i]);
                dfs(candidates, target - candidates[i], ans, combine, i);
                combine.remove(combine.size() - 1);
            }
        }
    }

}
