package com.google.ssmm.algorithm.leetcode.tiku;

public class n64 {
    //1 3 1
    //1 5 1
    //4 2 1
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][];
        dp[0] = new int [grid[0].length]; //这样写不是很好，因为是m * n 的，所以固定就好了
        dp[0][0] = grid[0][0];
        for(int i=1;i<grid.length;i++){
            dp[i] = new int[grid[i].length];
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }
        for(int i=1;i<grid[0].length;i++){
            dp[0][i] = grid[0][i] + dp[0][i-1];
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<grid[i].length;j++){
                dp[i][j] = grid[i][j]+ Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[dp.length-1][dp[dp.length-1].length-1];
    }

    public static void main(String[] args) {
        int [][] array = {{1,3,1},{1,5,1},{4,2,1}};
        int i = minPathSum(array);
        System.out.println(i);
    }
}
