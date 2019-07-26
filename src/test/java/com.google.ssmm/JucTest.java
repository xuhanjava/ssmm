package com.google.ssmm;

import org.apache.kafka.common.utils.CopyOnWriteMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JucTest {
    static CopyOnWriteMap<Integer, List<Integer>> map = new CopyOnWriteMap();

    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int rows;
    private int cols;

    public boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) return false;
        this.rows = rows;
        this.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (backtracking(matrix, str, marked, 0, i, j))
                    return true;

        return false;
    }

    private boolean backtracking(char[][] matrix, char[] str,
                                 boolean[][] marked, int pathLen, int r, int c) {

        if (pathLen == str.length) return true;
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || matrix[r][c] != str[pathLen] || marked[r][c]) {

            return false;
        }
        marked[r][c] = true;
        for (int[] n : next)
            if (backtracking(matrix, str, marked, pathLen + 1, r + n[0], c + n[1]))
                return true;
        marked[r][c] = false;
        return false;
    }

    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                matrix[r][c] = array[idx++];
        return matrix;
    }


    static void testPlay(int num){
        List<Integer> list = map.get(1);
        for(int i=num * 10000000;i<10000000 * (num+1);i++){
            list.add(i);
        }
    }

}
