package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n78 {
//    public List<List<Integer>> subsets(int[] nums) {
//
//    }

//    static List<Integer> t = new ArrayList<Integer>();
//    static List<List<Integer>> ans = new ArrayList<List<Integer>>();
//
//    static public List<List<Integer>> subsets(int[] nums) {
//        int n = nums.length;
//        for (int mask = 0; mask < (1 << n); ++mask) {
//            t.clear();
//            for (int i = 0; i < n; ++i) {
//                if ((mask & (1 << i)) != 0) {
//                    t.add(nums[i]);
//                }
//            }
//            ans.add(new ArrayList<Integer>(t));
//        }
//        return ans;
//    }
//
//
    static List<Integer> t = new ArrayList<Integer>();
    static List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public static List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public static void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }


}
