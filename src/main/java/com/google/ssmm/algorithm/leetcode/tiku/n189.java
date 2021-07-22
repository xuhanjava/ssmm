package com.google.ssmm.algorithm.leetcode.tiku;

public class n189 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        int count = 0;
        for (int start = 0; count < len; start++) {
            int prev = start;
            int current = start;
            while(true){
                current = (current + k) % len;
                int temp = nums[current];
                nums[current] = prev;
                prev = temp;
                count ++;
                if(current == start){
                    break;
                }
            }
        }
    }

    public class Solution {
        public void rotate(int[] nums, int k) {
            k = k % nums.length;
            int count = 0;
            for (int start = 0; count < nums.length; start++) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % nums.length;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                    count++;
                } while (start != current);
            }
        }
    }
}
