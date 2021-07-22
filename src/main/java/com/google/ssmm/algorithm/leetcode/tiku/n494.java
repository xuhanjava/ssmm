package com.google.ssmm.algorithm.leetcode.tiku;

//没写出来
public class n494 {
    int count;

    public int findTargetSumWays(int[] nums, int S) {
        huisu(nums, S, 0, 0);
        return count;
    }

    public void huisu(int[] nums, int s, int sum, int index) {
        if (index >= nums.length) {
            if (sum == s) {
                count++;
            }
            return;
        }
        huisu(nums, s, sum + nums[index], index + 1);
        huisu(nums, s, sum - nums[index], index + 1);
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    // dp[i][j]= dp[i-1][j-nums[i] + dp[i-1][j+nums[i]]
    public static int findTargetSumWays1(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][1000 + nums[0]] = 1;
        dp[0][1000 - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 2000; j >= 0; j--) {
                if (j - nums[i] < 0) {
                    dp[i][j] = dp[i - 1][j + nums[i]];
                } else if (j + nums[i] > 2000) {
                    dp[i][j] = dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][1000 + S];
    }

    public static void main(String[] args) {
        //[1,999]
        //998
        //输出：
        //0
        //预期结果：
        //1

        int[] nums = {1, 999};
        int targetSumWays1 = findTargetSumWays1(nums, 998);
        System.out.println(targetSumWays1);
    }
}
