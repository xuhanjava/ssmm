package com.google.ssmm.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {
    public static Integer[] sort(Integer[] a, int bucketCount) {
        if (a == null || a.length <= 1) {
            return a;
        }
        int maxValue = a[0], minValue = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > maxValue) {
                maxValue = a[i];
            } else if (a[i] < minValue) {
                minValue = a[i];
            }
        }
        int bucketSize = (int)Math.ceil(((float)maxValue - minValue) / bucketCount);
        BNode[] tempArray = new BNode[bucketCount];
        for (int i = 0; i < a.length; i++) {
            int index = (a[i] - minValue) / bucketSize;
            if (tempArray[index] == null || tempArray[index].list == null) { //debug看下值
                tempArray[index] = new BNode();
                tempArray[index].list = new ArrayList<>();
            }
            tempArray[index].list.add(a[i]);
        }
        //排序
        Arrays.stream(tempArray).filter(item ->item != null && item.list != null).forEach(item->{
            item.setList(GuiBinSort.guibin(item.list,0,item.list.size() -1)) ;
        });

        Integer[] result = new Integer[a.length];
        int i = 0;
        for (BNode item : tempArray) {
            for (Integer o : item.list) {
                result[i++] = o;
            }
        }
        return result;

    }

    public static void main(String[] args) {
//        int[][] a = new int[4][]; //不规则数组
//        a[0] = new int[]{1, 2, 3};
//
//        Arrays.stream(a).forEach(i -> {
//            Arrays.stream(i).forEach(j -> {
//                System.out.println(j);
//            });
//        });
        List<Integer> list = Lists.newArrayList(33,11,3,11,2,3,11,53,1111,3,2,12,31,56,788,97,4,32,458,10);
        Integer[] sort = sort(list.toArray(new Integer[list.size()]), 3);
        Arrays.stream(sort).forEach(item->{
            System.out.print(item +"_") ;
        });
    }

    private static class BNode {
        private List<Integer> list;


        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "BNode{" +
                    "list=" + list +
                    '}';
        }
    }
}
