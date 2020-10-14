package com.google.ssmm.algorithm.leetcode.dongtai;

/**
 *
 */
public class OneAndZero {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0) {
            return 0;
        }
        int[][] status = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                status[i][j] = -1;
            }
        }
        //设置为-1         String [] array ={"10", "0001", "111001", "1", "0"};
        status[0][0] = 0;
        for (int item = 0; item < strs.length; item++) {
            int count0 = count(strs[item], '0');
            int count1 = strs[item].length() - count0;
            if (count0 > m || count1 > n) {
                continue;
            }
            for (int i = m - count0; i >=0 ; i--) {
                for (int j = n - count1; j >=0 ; j--) {
                    if (status[i][j] >= 0) {
                        //查找0有几个，1：有几个
                        //判断加完之后是不是大于了m,n
                        status[i + count0][j + count1] = Math.max(status[i][j] + 1, status[i + count0][j + count1]);
                    }
                }
            }

        }

        int maxValue = 0;
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                maxValue = Math.max(maxValue, status[i][j]);
            }
        }
        return maxValue;
    }

    private int count(String str, char key) {
        if (str.length() == 0) {
            return 0;
        }
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == key) {
                count++;
            }
        }
        return count;
    }
}
