package com.google.ssmm;

import com.google.ssmm.test.AopTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        System.out.println(getIndex(new int[]{1,3,5,7,2,1},3));
    }

    public static int getIndex(int[] array,int target){
        if(array == null || array.length ==0){
            return -1;
        }
        int left = 0;
        int right = array.length-1;
        return search(array,target,left,right);
    }

    public static int search(int[] array,int target,int left,int right){
        while(left <=right){
            int middle = (left + right)/2;
            if(array[middle] == target){
                return middle;
            }
            if(array[middle]>target){
                if(isInLeft(array,middle)){
                    right = middle -1;
                    return search(array,target,left,right);
                }else{
                    left = middle +1;
                    return search(array,target,left,right);

                }
            }else{
                if(isInLeft(array,middle)){
                    left = middle +1;
                    return search(array,target,left,right);

                }else{
                    right = middle -1;
                    return search(array,target,left,right);
                }
            }
        }
        return -1;
    }

    public static boolean isInLeft(int[]array,int index){
        if(index == 0){
            return true;
        }
        for(int i=index;i>0;i--){
            if(array[i-1] < array[i]){
                return true;
            }
        }
        return false;
    }
}
