package com.google.ssmm.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

public class QuickSort {
    public static void sort(List<Integer> list,int begin,int end) {
        if(begin >= end){
            return;
        }
        int pivot = merge(list,begin,end);
        sort(list,begin,pivot-1); //这里-1为啥有问题呢
        sort(list,pivot +1,end); //同理这里pivot 不加1会有啥问题；永远跳不出循环
    }

    private static int merge(List<Integer> list, int begin, int end) {
        int pivot = list.get(end);
        int i = begin;
        for (int j=begin;j<end;j++){//是不是<
            if(list.get(j)< pivot){ //是不是=
                swap(list,i,j);
                i++;
            }
        }
        swap(list,i,end);
        return i;
    }

    private static void swap(List<Integer> list, int left, int right){
        int temp = list.get(left);
        list.set(left,list.get(right));
        list.set(right,temp);
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(33,11,3,11,2,3,11,53);
        sort(list,0,list.size()-1);
        System.out.println(list);
    }
}
