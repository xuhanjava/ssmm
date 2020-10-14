package com.google.ssmm.algorithm.sort;

import java.util.Random;

public class SortTest {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test2(){
        int [] array = {0,3,3,1,1,3,333,1,333,12,35,6,78,9,4,14,6};
        HeapSort.sort(array);
    }

    private static void test1() {
        int capacity = 10;
        Random random = new Random();
        Heap heap = new Heap(capacity);
        for (int i=0;i<capacity;i++){
            heap.insertValue(random.nextInt(30));
        }
        heap.sort();

        System.out.println("-----------------------------------");
        int [] array = {0,3,3,1,1,3,333,1,333,12,35,6,78,9,4,14,6};
        heap.heapSort(array);
        Heap heap1 = new Heap(array,array.length -1);
        heap1.sort();
    }
}
