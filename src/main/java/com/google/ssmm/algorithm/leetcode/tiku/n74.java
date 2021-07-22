package com.google.ssmm.algorithm.leetcode.tiku;

public class n74 {

    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // 二分查找
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) return true;
            else {
                if (target < pivotElement) right = pivotIdx - 1;
                else left = pivotIdx + 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1;
        if (m < 0) {
            return false;
        }
        int n = matrix[0].length - 1;
        int beginM = 0;
        int beginN = 0;
        int endM = m;
        int endN = n;
        int middleM = 0;
        if (n < 0) {
            return false;
        }

        while (beginM <= endM) {
            middleM = (beginM + endM) / 2;
            if (matrix[middleM][0] == target) {
                return true;
            } else if (matrix[middleM][0] < target) {
                beginM = middleM + 1;
            } else {
                endM = middleM - 1;
            }
        }
        if (matrix[middleM][0] > target && middleM != 0) {
            middleM--;
        }

        while (beginN <= endN) {
            int middleN = (beginN + endN) / 2;
            if (matrix[middleM][middleN] == target) {
                return true;
            } else if (matrix[middleM][middleN] < target) {
                beginN = middleN + 1;
            } else {
                endN = middleN - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        boolean b = searchMatrix(array, 11);
        System.out.println(b);
    }
}
