package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n54 {
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int size = 0;


    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        boolean[][] trueResult = new boolean[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            trueResult[i] = new boolean[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                size++;
            }
        }
        int indexRow = 0;
        int indexColumn = 0;
        diguiTrue(matrix, result, indexRow, indexColumn, 0, trueResult);
        return result;
    }

    private static void diguiTrue(int[][] matrix, List<Integer> result, int indexRow, int indexColumn, int direct, boolean[][] trueResult) {
        if (result.size() == size) {
            return;
        }
        result.add(matrix[indexRow][indexColumn]);
        trueResult[indexRow][indexColumn] = true;
        if (indexRow + directions[direct][0] >= matrix.length || indexRow + directions[direct][0] < 0 || indexColumn + directions[direct][1] >= matrix[indexRow].length || indexColumn + directions[direct][1] < 0) {
            int temp = (direct + 1) % 4;
            diguiTrue(matrix, result, indexRow + directions[temp][0], indexColumn+ directions[temp][1], temp, trueResult);
            return;
        }
        if (trueResult[indexRow + directions[direct][0]][indexColumn + directions[direct][1]]) {
            int temp = (direct + 1) % 4;
            diguiTrue(matrix, result, indexRow+directions[temp][0], indexColumn+directions[temp][1], temp, trueResult);
            return;
        }
        diguiTrue(matrix, result, indexRow + directions[direct][0], indexColumn + directions[direct][1], direct, trueResult);

    }

    private static void digui(int[][] matrix, List<Integer> result, int indexRow, int indexColumn, int direct, boolean[][] trueResult) {
        if (indexRow >= matrix.length || indexColumn >= matrix[indexRow].length || indexColumn < 0 || indexRow < 0) {
            //direct +1
            digui(matrix, result, indexRow - directions[direct][0] + directions[direct + 1][0], indexColumn - directions[direct][1] + directions[direct + 1][1], direct + 1, trueResult);
            return;
        }
        if (trueResult[indexRow][indexColumn]) {
            if (direct == 3) {
                digui(matrix, result, indexRow + 1, indexColumn + 1, 0, trueResult);
                return;
            } else {
                //direct + 1
                digui(matrix, result, indexRow - directions[direct][0], indexColumn - directions[direct][1], direct + 1, trueResult);
                return;
            }
        }
        result.add(matrix[indexRow][indexColumn]);
        trueResult[indexRow][indexColumn] = true;
        if (result.size() == size) {
            return;
        }
        digui(matrix, result, indexRow + directions[direct][0], indexColumn + directions[direct][1], direct, trueResult);
    }

    //[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]

    //[1,2,3,4,8,12,16,15,14,13,9,5,10,11,7,6]

    //预期[1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
    //   [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
    public static void main(String[] args) {
        //[
        // [ 1, 2, 3 ],
        // [ 7, 8, 9 ]
        //]        // [ 4, 5, 6 ],
        //输出: [1,2,3,6,9,8,7,4,5]
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println(spiralOrder(matrix));
    }


}
