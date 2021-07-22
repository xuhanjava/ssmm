package com.google.ssmm.algorithm.leetcode.tiku.子序列动态规划;

public class n718 {
    //输入：
    //A: [1,2,3,2,1]
    //B: [3,2,1,4,7]
    //输出：3
    //解释：
    //长度最长的公共子数组是 [3, 2, 1] 。
    public static int findLength(int[] A, int[] B) {
        int [][] dp = new int[A.length][B.length];
        for(int j=0;j<A.length;j++){
            if(A[0] == B[j]){
                dp[0][j] = 1;
            }
        }
        for(int i=0;i<A.length;i++){
            if(A[i] == B[0]){
                dp[i][0] = 1;
            }
        }
        for(int i=1;i<A.length;i++){
            for(int j =1;j<B.length;j++){
                if(dp[i-1][j-1] >0){
                    if(A[i] == B[j]){
                        dp[i][j] = dp[i-1][j-1] +1;
                    }
                }else if(A[i] == B[j]){
                    dp[i][j] = 1;
                }
            }

        }
        int max = 0;
        for(int i = 0;i<A.length;i++){
            for(int j =0 ;j<B.length;j++){
                max = Math.max(dp[i][j],max);
            }
        }
        return max;

    }

    public static void main(String[] args) {
        int [] A = {1,2,3,2,1};
        int [] B = {3,2,1,4,7};
        int length = findLength(A, B);
        System.out.println(length);
    }
}
