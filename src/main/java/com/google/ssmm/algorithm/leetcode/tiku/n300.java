package com.google.ssmm.algorithm.leetcode.tiku;

public class n300 {
    //status[j] = status[j-1] +1
    public static int lengthOfLIS(int arr[]) {
        if (arr.length == 0) {
            return 0;
        }
        int[] status = new int[arr.length + 1];
        for (int i = 0; i < status.length; i++) {
            status[i] = 1;
        }
        //status[i] = status[]
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (status[j] > 0 && arr[j] < arr[i]) {
                    status[i] = Math.max(status[j] + 1, status[i]);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(status[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(array));
    }
}
