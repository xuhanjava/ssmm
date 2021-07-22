package com.google.ssmm.algorithm.leetcode.tiku;


import java.util.HashSet;
import java.util.Set;

//[['A','B','C','E'],
// ['S','F','C','S'],
// ['A','D','E','E']]
//'ABCB'
// 要注意的是记录下访问过的set，防止查询abcb这样的重复访问到
public class n79 {
    public static boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == chars[0]) {
                    set.add(i+"_"+j);
                    if (check(board, chars, i, j, 0,set)) {
                        return true;
                    }
                    set.remove(i+"_"+j);

                }
            }
        }
        return false;
    }

    public static boolean check(char[][] board, char[] chars, int i, int j, int index,Set set) {
        if (index >= chars.length - 1) {
            return true;
        }
        if(i >= board.length || j>= board[i].length){
            return false;
        }
        if (i <= board.length-2 && board[i + 1][j] == chars[index + 1] && !set.contains((i+1)+"_"+j)) {
            set.add((i+1)+"_"+j);
            if(check(board, chars, i + 1, j, index + 1,set)){
                return true;
            }
            set.remove((i+1)+"_"+j);

        }
        if (i>=1 && board[i - 1][j] == chars[index + 1] && !set.contains((i-1)+"_"+j)) {
            set.add((i-1)+"_"+j);
            if (check(board, chars, i - 1, j, index + 1,set)){
                return true;
            }
            set.remove((i-1)+"_"+j);

        }
        if (j<=board[i].length-2 && board[i][j + 1] == chars[index + 1] && !set.contains(i+"_"+(j+1)) ){
            set.add(i+"_"+(j+1));
            if(check(board, chars, i, j + 1, index + 1,set)){
                return true;
            }
            set.remove(i+"_"+(j+1));

        }
        if (j>=1 && board[i][j - 1] == chars[index + 1] && !set.contains(i+"_"+(j-1))) {
            set.add(i+"_"+(j-1));
            if(check(board, chars, i, j - 1, index + 1,set)){
                return true;
            }
            set.remove(i+"_"+(j-1));
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] array = {{'A'},{'A'}};
        boolean abcb = exist(array, "AAA");
        System.out.println(abcb);
    }



    class Solution {
        public boolean exist(char[][] board, String word) {
            int h = board.length, w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    boolean flag = check(board, visited, i, j, word, 0);
                    if (flag) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
            if (board[i][j] != s.charAt(k)) {
                return false;
            } else if (k == s.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean result = false;
            for (int[] dir : directions) {
                int newi = i + dir[0], newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        boolean flag = check(board, visited, newi, newj, s, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            visited[i][j] = false;
            return result;
        }
    }

}
