package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.List;

public class n120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] status = new int[triangle.size()][triangle.size()];
        status[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < status.length; i++) {
            List<Integer> tempList = triangle.get(i);
            for (int j = 0;j<tempList.size();j++ ) {
                if (j == 0) {
                    if (status[i - 1][j] > 0) {
                        status[i][j] = status[i - 1][j] + tempList.get(0);
                    }
                } else {
                    if (status[i - 1][j] > 0) {
                        if (status[i][j] == 0) {
                            status[i][j] = status[i - 1][j] + tempList.get(j);
                        } else {
                            status[i][j] = Math.min(status[i - 1][j] + tempList.get(j), status[i][j]);
                        }
                    }
                    if (status[i - 1][j - 1] > 0) {
                        if (status[i][j] == 0) {
                            status[i][j] = status[i - 1][j - 1] + tempList.get(j);
                        }else{
                            status[i][j] = Math.min(status[i - 1][j - 1] + tempList.get(j), status[i][j]);
                        }
                    }
                }
            }
        }
        int minV = status[triangle.size() -1][0];
        for(int i=1;i<status[triangle.size()-1].length;i++){
            minV = Math.min(minV,status[triangle.size() -1][i]);
        }
        return minV;
    }
}

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }
}