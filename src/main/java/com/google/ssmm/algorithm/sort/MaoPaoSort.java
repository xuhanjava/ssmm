package com.google.ssmm.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

public class MaoPaoSort {
    public static void sort(List<Integer> list){
        for (int i = 0;i<list.size();i++){
            for(int j=1;j<list.size() - i;j++){
                if (list.get(j-1) > list.get(j)){
                    int temp = list.get(j-1);
                    list.set(j-1,list.get(j));
                    list.set(j,temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(3,11,2,3,11,53);
        sort(list);
        System.out.println(list);
    }

}
