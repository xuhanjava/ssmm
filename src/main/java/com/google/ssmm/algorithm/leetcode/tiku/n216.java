package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n216 {
    static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum3(3, 7);
        System.out.println(lists);
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, 9, k, n,new ArrayList<>());
        return result;
    }

    public static void dfs(int start, int end, int k, int n, List<Integer> temp) {

        if (temp.size() == k) {
            int tempSum = 0;
            for (int i = 0; i < temp.size(); i++) {
                tempSum += temp.get(i);
            }
            if (tempSum == n) {
                List<Integer> list = new ArrayList<>(temp);
                result.add(list);
            }
            return;
        }
        for (int i = start; i < end; i++) {
            temp.add(i);
            dfs(i+1,end,k,n,temp);
            temp.remove(temp.size()-1);
        }
    }
}
