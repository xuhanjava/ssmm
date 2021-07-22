package com.google.ssmm.algorithm.leetcode.tiku;

//输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2

//想法分析，这个地方我第一时间想到的是回溯；但是其实dp status[i][j] = Math.max(status[i-1][j],status[i][j-1])
public class n63 {
    static int count;
    static int ac;

    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int[][] status = new int[obstacleGrid.length][];
        boolean rowBool = true;
        for(int i=0;i<obstacleGrid.length;i++){
            status[i] = new int[obstacleGrid[i].length];
            if(obstacleGrid[i][0] == 1){
                rowBool = false;
            }
            if (rowBool){
                status[i][0] = 1;
            }
        }
        for(int i=0;i<obstacleGrid[0].length;i++){
            if(obstacleGrid[0][i] == 1){
                break;
            }
            status[0][i] = 1;
        }

        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                if (status[i - 1][j] > 0) {
                    status[i][j] += status[i - 1][j];
                }
                if (status[i][j - 1] > 0) {
                    status[i][j] += status[i][j-1];
                }
            }
        }
        return status[obstacleGrid.length -1 ][obstacleGrid[obstacleGrid.length -1].length-1];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int i = 0, j = 0;
        f(i, j, obstacleGrid);
        return count;
    }

    public static void f(int i, int j, int[][] obs) {
        if (obs[i][j] == 1) {
            return;
        }
        ac ++;
        if (i == obs.length - 1 && j == obs[obs.length - 1].length - 1) {
            count++;
            return;
        }
        if (i + 1 < obs.length && obs[i + 1][j] != 1) {
            f(i + 1, j, obs);
        }
        if (j + 1 < obs[i].length && obs[i][j + 1] != 1) {
            f(i, j + 1, obs);
        }
    }

    public static void main(String[] args) {
        int[][] array = {{0,0,0,0,0},{0,1,0,0,0},{0,0,0,0,0},{0,1,1,0,0},{0,0,0,0,0}};
        System.out.println(uniquePathsWithObstacles(array));
        System.out.println(ac);
    }
}
