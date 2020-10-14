package com.google.ssmm.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

public class InsertSort {
    public static void sort(List<Integer> list) {
        for (int i=1;i<list.size();i++){
            int temp = list.get(i);
            int j = i-1;
            for (;j>= 0;j--){
                if (list.get(j)>temp){
                    list.set(j+1,list.get(j));
                }else{
                    break;
                }
            }
            list.set(j+1,temp);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(3,11,2,3,11,53);
        sort(list);
        System.out.println(list);
    }
}
