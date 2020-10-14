package com.google.ssmm.algorithm.search;

public class BinarryTest {
    //非重复元素的二分查找
    public static int binarySearch(int []nums,int start,int subEnd,int target){
        int begin =start,end = subEnd;
        while(begin <= end){
            int middle = (end + begin) / 2;
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle] > target){
                end = middle - 1;
            }else {
                begin = middle +1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
