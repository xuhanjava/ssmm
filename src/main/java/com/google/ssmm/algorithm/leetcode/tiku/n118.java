package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        for (int i = 1; i < numRows; i++) {
            List<Integer> itemList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                Integer v;
                if(j==0){
                    v = 1;
                }else if( j == i){
                    v = 1;
                } else{
                    v = result.get(i - 1).get(j - 1) + result.get(i-1).get(j);
                }
                itemList.add(v);
            }
            result.add(itemList);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> generate = generate(5);
        System.out.println(generate);
    }
}
