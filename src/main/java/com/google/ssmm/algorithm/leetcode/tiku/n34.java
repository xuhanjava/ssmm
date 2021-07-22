package com.google.ssmm.algorithm.leetcode.tiku;

public class n34 {

    public static int[] searchRange(int[] nums, int target) {
        int begin = 0, end = nums.length - 1;
        int[] result = new int[2];
        result[0] = binarySearch(nums, begin, end, target, true);
        result[1] = binarySearch(nums, begin, end, target, false);
        return result;

    }

    public static int binarySearch(int[] nums, int begin, int end, int target, boolean needLeft) {
        while (begin <= end) {
            int middle = (begin + end) / 2;
            //这个地方犯了严重的错误，if之后下面直接 else if(needLeft)了，肯定是不对的，
            if (nums[middle] == target) {
                if (needLeft) {
                    if (middle == 0 || nums[middle - 1] != target) {
                        return middle;
                    }
                } else {
                    if (middle == end || nums[middle + 1] != target) {
                        return middle;
                    }
                }
            }
            if (needLeft) {
                if (nums[middle] < target) {
                    begin = middle + 1;
                } else {
                    end = middle - 1;
                }
            } else {
                if (nums[middle] <= target) {
                    begin = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{};
        int[] ints = searchRange(nums, 71);
        System.out.println(ints);
    }
}
