package com.google.ssmm.algorithm.leetcode.tiku;

//["1","1","1"],
//["0","1","0"],
//["1","1","1"]]
public class n200 {
    public static int numIslands(char[][] grid) {
        boolean[][] flag = new boolean[grid.length][];
        int result = 0;
        for (int j = 0; j < grid.length; j++) {
            flag[j] = new boolean[grid[j].length];
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && flag[i][j] != true) {
                    flag[i][j] = true;
                    search(grid,flag,i,j,grid.length,grid[0].length);
                    result++;
                }
            }
        }
        return result;
    }

    public static void search(char[][] grid,boolean[][] flag,int i,int j,int rowLen,int cowLen){
        if(i +1 < rowLen && grid[i+1][j] == '1' && flag[i+1][j] != true){
            flag[i+1][j] = true;
            search(grid,flag,i+1,j,rowLen,cowLen);
        }
        if(i -1 >=0  && grid[i-1][j] == '1' && flag[i-1][j] != true){
            flag[i-1][j] = true;
            search(grid,flag,i-1,j,rowLen,cowLen);
        }
        if(j+1 <cowLen && grid[i][j+1] == '1' && flag[i][j+1] != true){
            flag[i][j+1] = true;
            search(grid,flag,i,j+1,rowLen,cowLen);
        }
        if(j-1 >=0 && grid[i][j-1] == '1' && flag[i][j-1] != true){
            flag[i][j-1] = true;
            search(grid,flag,i,j-1,rowLen,cowLen);
        }
    }

    public static void main(String[] args) {
        char[][] array ={{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        numIslands(array);
    }
}
