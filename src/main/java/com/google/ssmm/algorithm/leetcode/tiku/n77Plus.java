package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n77Plus {

    List<List<Integer>> result = new ArrayList<>();
    public  List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<>();
        dfs(1,n,k,temp);
        return result;
    }

    public  void dfs(int index,int n,int k,List<Integer> temp){
        if(n - index +1 < k-temp.size()){
            return;
        }
        if(temp.size() == k){
            result.add(new ArrayList<>(temp));
            return;
        }
        if(index > n+1){
            return;
        }
        temp.add(index);
        dfs(index+1,n,k,temp);
        temp.remove(temp.size() -1 );
        dfs(index+1,n,k,temp);
    }

    public static void main(String[] args) {
//        List<List<Integer>> combine = combine(5, 3);
//        System.out.println(combine);
    }
}
