package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.*;
//给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，
// 计算按此排列的柱子，下雨之后能接多少雨水。(数组以外的区域高度视为0)
//https://www.nowcoder.com/practice/31c1aed01b394f0b8b7734de0324e00f?tpId=117&tqId=37802&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26pageSize%3D50%26search%3D%25E9%259B%25A8%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117&difficulty=undefined&judgeStatus=undefined&tags=&title=%E9%9B%A8
public class water {
    public long maxWater(int[] arr) {
        ArrayList arrayList = new ArrayList();
        Deque<Integer> path = new LinkedList<Integer>();
        path.pollLast();
        //排除空数组
        if (arr.length == 0)
            return 0;
        long res = 0;
        //左右双指针
        int left = 0;
        int right = arr.length - 1;
        //中间区域的边界高度
        int maxL = 0;
        int maxR = 0;
        //直到左右指针相遇
        while (left < right) {
            //每次维护往中间的最大边界
            maxL = Math.max(maxL, arr[left]);
            maxR = Math.max(maxR, arr[right]);
            //较短的边界确定该格子的水量
            if (maxR > maxL)
                res += maxL - arr[left++];
            else
                res += maxR - arr[right--];
        }
        return res;
    }

}
