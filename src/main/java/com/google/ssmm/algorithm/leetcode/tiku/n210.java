package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++){
            for(int j=0;j<prerequisites[i].length;j++){
                edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }
        }
        return null;
    }
}
