package com.google.ssmm.algorithm.sort;

//数据从下标1开始，0不存储数据；第二种堆排序建表我是通过debug实现的
//大顶堆
public class Heap {
    private int[] array;
    //堆中已存数据
    private int count;

    public Heap() {
    }

    public Heap(int capacity) {
        this.array = new int[capacity];
    }

    public Heap(int[] array, int count) {
        this.array = array;
        this.count = count;
    }

    public boolean insertValue(int value) {
        if (array.length - 1 <= count) {
            return false;
        }
        array[++count] = value;
        int num = count;
        while (num > 1) {
            if (array[num / 2] < value) {
                swap(array, num, num / 2);
                num = num / 2;
            } else {
                break;
            }
        }
        return true;
    }

    public boolean removeTop() {
        if (count < 1) {
            return false;
        }
        array[1] = array[count];
        int begin = 1;
        int maxValue = array[1];
        int maxItem = 1;
        while (begin * 2 <= count && begin * 2 + 1 <= count) {
            if (maxValue < array[begin * 2]) {
                maxValue = array[begin * 2];
                maxItem = begin * 2;
            }
            if (maxValue < array[begin * 2 + 1]) {
                maxValue = array[begin * 2 + 1];
                maxItem = begin * 2 + 1;
            }
            if (maxValue == array[begin]) {
                count--;
                return true;
            }
            maxValue = array[begin];
            swap(array, begin, maxItem);
            begin = maxItem;
        }
        count--;
        return true;
    }

    //遍历的结果就是删除吗？那么动态添加不就是有问题吗
    public void sort() {
        while (count > 0) {
            System.out.println(array[1]);
            removeTop();
        }
    }

    private static void swap(int[] list, int left, int right) {
        int temp = list[left];
        list[left] = list[right];
        list[right] = temp;
    }


    /**
     *  private static void buildHeap(int[] a, int n) {
     *   for (int i = n/2; i >= 1; --i) {
     *     heapify(a, n, i);
     *   }
     * }
     * private static void heapify(int[] a, int n, int i) {
     *   while (true) {
     *     int maxPos = i;
     *     if (i*2 <= n && a[i] < a[i*2]) maxPos = i*2;
     *     if (i*2+1 <= n && a[maxPos] < a[i*2+1]) maxPos = i*2+1;
     *     if (maxPos == i) break;
     *     swap(a, i, maxPos);
     *     i = maxPos;
     * } }
      * @param array
     */


    public void heapSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        this.count = array.length - 1;
        int index = count / 2;
        while (index >= 1) {
            int indexSecond = index;
            while (true) {
                int maxValue = array[indexSecond];
                int maxIndex = indexSecond;
                if (indexSecond * 2 < array.length) {
                    if (maxValue < array[indexSecond * 2]) {
                        maxValue = array[indexSecond * 2]; //maxValue是多余的
                        maxIndex = indexSecond * 2;
                    }
                }
                if (indexSecond * 2 + 1 < array.length) {
                    if (maxValue < array[indexSecond * 2 + 1]) {
                        maxIndex = indexSecond * 2 +1;
                    }
                }
                if (indexSecond == maxIndex) {
                    break;
                } else {
                    swap(array, indexSecond, maxIndex);
                    indexSecond = maxIndex;
                }

            }
            index--;
        }

    }
}
