package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class n279 {
    Set<Integer> square_nums = new HashSet<Integer>();

    protected boolean is_divided_by(int n, int count) {
        if (count == 1) {
            return square_nums.contains(n);
        }

        for (Integer square : square_nums) {
            if (is_divided_by(n - square, count - 1)) {
                return true;
            }
        }
        return false;
    }

    public int numSquares12(int n) {
        this.square_nums.clear();

        for (int i = 1; i * i <= n; ++i) {
            this.square_nums.add(i * i);
        }

        int count = 1;
        for (; count <= n; ++count) {
            if (is_divided_by(n, count))
                return count;
        }
        return count;
    }

    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int square_nums[] = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }

    public static int numSquares1(int n) {
        int sqrt = (int) Math.sqrt(n) + 1;
        int[] nums = new int[sqrt];
        int[] dp = new int[n + 1];
        for (int i = 0; i < sqrt; i++) {
            nums[i] = i * i;
            dp[nums[i]] = 1;
        }
        if (dp[n] == 1) {
            return 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < sqrt; j++) {
                if (dp[i] > 0 && i + nums[j] <= n) {
                    if (dp[i + nums[j]] == 0) {
                        dp[i + nums[j]] = dp[i] + 1;
                    } else {
                        dp[i + nums[j]] = Math.min(dp[i + nums[j]], dp[i] + 1);
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int i = numSquares1(17);
        System.out.println(i);
    }
}
