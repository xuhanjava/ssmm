package com.google.ssmm.algorithm.leetcode.tiku;

public class n215_test {
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return nums[quickSort(nums, k, 0, nums.length - 1)];
    }

    public static int quickSort(int[] nums, int k, int begin, int end) {
        if(begin >= end){
            return end;
        }
        int pivot = merge(nums, k, begin, end);
        if (pivot + 1 == k) {
            return pivot;
        } else if (pivot + 1 > k) {
            return quickSort(nums, k, begin, pivot - 1);
        } else {
            return quickSort(nums, k, pivot + 1, end);
        }
    }

    public static int merge(int[] nums, int k, int begin, int end) {
        int piot = nums[end];
        int j = begin;
        for (int i = begin; i < end; i++) {
            if (nums[i] > piot) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
        int temp = nums[j];
        nums[j] = nums[end];
        nums[end] = temp;
        return j;
    }

    public static void main(String[] args) {
        int [] nums= {3,2,1,5,6,4};
        int kthLargest = findKthLargest(nums, 2);
        System.out.println(kthLargest);
    }
}
