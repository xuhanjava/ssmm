package com.google.ssmm.algorithm.search;

import com.google.common.collect.Lists;

import java.util.List;

public class BinarySearch {
    //非重复元素查询
    public static int binarySearchA(Integer[] a, int value) {
        int begin = 0, end = a.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;//begin + ((end - begin) >> 1); 防止数组溢出
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    //重复元素取第一个1，2，2，3，4，5  value:2
    public static int binarySearchB(Integer[] a, int value) {
        int begin = 0, end = a.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2; //begin + ((end - begin) >> 1);
            if (a[mid] > value) {
                end = mid - 1;
            } else if (a[mid] < value) {
                begin = mid + 1;
            } else if (mid == 0 || a[mid - 1] < value) {
                return mid;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //获取第一个大于等于给定值的 1，3，3，5，6 value:2
    public static int binarySearchC(Integer[] a, int value) {
        int begin = 0, end = a.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            if (a[mid] >= value &&(mid == 0 || a[mid-1]<value)) {
                return mid;
            } else if (a[mid] < value) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        //testA();
        //testB();
        testC();
    }

    private static void testC() {
        List<Integer> list = Lists.newArrayList(3, 3, 3, 5, 5, 7, 9);
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println(binarySearchC(array, 2));
    }

    private static void testB() {
        List<Integer> list = Lists.newArrayList(3, 3, 3, 5, 5, 7, 9);
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println(binarySearchB(array, 3));
    }

    private static void testA() {
        List<Integer> list = Lists.newArrayList(2, 3, 4, 5, 6, 7, 9);
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println(binarySearchA(array, 2));
    }
}
