package com.google.ssmm.algorithm.leetcode.tiku;

import com.google.ssmm.algorithm.search.BinarryTest;

public class n33 {
    //1,2,3,4,5,6,7 -> 6,7,1,2,3,4,5
   static public int search(int[] nums, int target) {
        if(nums.length == 0){
            return -1;
        }
        //length = 1,2
        int begin = 0,end = nums.length -1;
        return display(nums,begin,end,target);

    }

   static int display(int[] nums,int begin,int end,int target){
        if(begin > end){
            return -1;
        }
        int middle = (end + begin)/ 2;
        if(nums[middle] == target){
            return middle;
        } else if(nums[middle] > nums[begin]){
            //二分查找
            //(begin,middle)
            int index = BinarryTest.binarySearch(nums, begin, middle - 1, target);
            if(index >=0){
                return index;
            }
            //遍历
            return display(nums,middle+1,end,target);
        }else{
            //反之
            int index = BinarryTest.binarySearch(nums, middle+1, end, target);
            if(index >=0){
                return index;
            }
            //遍历
            return display(nums,begin,middle-1,target);
        }
    }

    public static void main(String[] args) {
       int[] nums =new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums,7));
    }
}
