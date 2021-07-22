package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class n56 {
    //给出一个区间的集合，请合并所有重叠的区间。
    //
    // 
    //
    //示例 1:
    //
    //输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
    //输出: [[1,6],[8,10],[15,18]]
    //解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    //示例 2:
    //
    //输入: intervals = [[1,4],[4,5]]
    //输出: [[1,5]]
    //解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
    //注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
    //
    // 
    //
    //提示：
    //
    //intervals[i][0] <= intervals[i][1],但是intervals 每个元素左端点不是按照升序排列的

    public int[][] merge(int[][] intervals) {
        return null;
    }

    public static void main(String[] args) {

    }

    public int[][] merge1(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

}
