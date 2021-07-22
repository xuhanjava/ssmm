package com.google.ssmm.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class GuiBinSort {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(33,11,3,11,2,3,11,53);
        System.out.println(guibin(list,0,list.size()-1));
    }

    public static List<Integer> guibin(List<Integer> list, int start, int end) {
        if (start >= end) {
            return Lists.newArrayList(list.get(start));
        }
        int mid = (start + end) / 2;
        List<Integer> left = guibin(list, start, mid);
        List<Integer> right = guibin(list, mid + 1, end);
        return merge(left, right);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> tempList = new ArrayList<>(left.size() + right.size());
        int l=0,r = 0;
        for(;l<left.size() && r<right.size();){
            if(left.get(l) >= right.get(r)){
                tempList.add(right.get(r++)); //之前写的是tempList.add(index,length.get(r++)) && tempList.set(index,length.get(r++))
                continue;
            }
            tempList.add(left.get(l++));
        }
        if (l == left.size() && r <right.size()){
            for(int i=r;i<right.size();i++){
                tempList.add(right.get(i));
            }
        }
        if (r == right.size() && l <left.size()){
            for(int i=l;i<left.size();i++){
                tempList.add(left.get(i));
            }
        }
        return tempList;
    }
}
