package com.google.ssmm.algorithm.leetcode.tiku;

public class n80 {
    //[1,1,1,2,2,2,3,3]
    public int removeDuplicates(int[] nums) {

        return 0;
    }

    public static int removeDuplicates1(int[] nums) {

        //
        // Initialize the counter and the second pointer.
        //
        int j = 1, count = 1;

        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        for (int i = 1; i < nums.length; i++) {

            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            //
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            //
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }




    public static void main(String[] args) {
        int [] nums = {1,1,1,1,1,2,2,3};
        System.out.println(removeDuplicates1(nums));
        System.out.println(nums);
    }
}
