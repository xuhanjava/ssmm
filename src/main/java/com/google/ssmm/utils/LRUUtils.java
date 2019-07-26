package com.google.ssmm.utils;

import java.util.LinkedHashMap;

public class LRUUtils<K, V> extends LinkedHashMap<K, V> {
    public LRUUtils(int maxSize) {
        super(maxSize, 0.75F, true);
        maxElements = maxSize;
    }

    protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
        //逻辑很简单，当大小超出了Map的容量，就移除掉双向队列头部的元素，给其他元素腾出点地来。
        return size() > maxElements;
    }

    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public static void main(String[] args) {
        LRUUtils<Integer,Integer> lruUtils = new LRUUtils(2);
        for (int i = 0; i < 5; i++) {
            lruUtils.put(i,i);
        }
        lruUtils.forEach((key,value)->{
            System.out.println("key:"+key+"----"+"value:"+value);
        });

    }
}
