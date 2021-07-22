package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;


//[2,7,6,3,5,1]
//9
//输出：[[2,2,2,2,1],[2,2,2,3],[2,2,3,1,1],[2,2,5],[2,7],[7,1,1],[6,3],[3,3,3],[3,5,1],[3,1,1,1,1,1,1],[5,1,1,1,1],[1,1,1,1,1,1,1,1,1]]
//预期：[[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,2],[1,1,1,1,1,1,3],[1,1,1,1,1,2,2],[1,1,1,1,2,3],[1,1,1,1,5],[1,1,1,2,2,2],[1,1,1,3,3],[1,1,1,6],[1,1,2,2,3],[1,1,2,5],[1,1,7],[1,2,2,2,2],[1,2,3,3],[1,2,6],[1,3,5],[2,2,2,3],[2,2,5],[2,7],[3,3,3],[3,6]]
public class n39 {
    static List<List<Integer>> result = new ArrayList<>();
    static public  List<List<Integer>> combinationSum(int[] candidates, int target) {
        display(candidates,0,new ArrayList<>(),0,target);
        return result;
    }

    static void display(int[] candidates,int index,List<Integer> temp,int weight,int target){
        for(int i=index;i<candidates.length;i++){
            if(temp.size() == 3 && temp.get(temp.size() -1) == 2){
                System.out.println(123);
            }
            if(candidates[i] == target - weight){
                temp.add(candidates[i]);
                result.add(new ArrayList<>(temp));
                temp.remove(temp.size()-1);
                continue;
            }else if(candidates[i] > target - weight){
                continue;
            }else{
                temp.add(candidates[i]);
                display(candidates,i,temp,weight+candidates[i],target);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int [] array = {2,7,6,3,5,1};
        List<List<Integer>> lists = combinationSum(array, 9);
        System.out.println(lists);
    }
}
