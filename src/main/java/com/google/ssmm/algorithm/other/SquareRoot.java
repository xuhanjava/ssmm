package com.google.ssmm.algorithm.other;

import java.util.LinkedHashMap;
import java.util.Map;

public class SquareRoot {
    public static void main(String[] args) {
        //注意accessOrder true才是lru 内部原理哈希表+链表
        Map<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
        m.put(3, 11);
        m.put(1, 12);
        m.put(5, 23);
        m.put(2, 22);
        m.put(3, 26);
        m.get(5);
        for (Map.Entry e : m.entrySet()) {
            System.out.println(e.getKey());
        }
    }
//
//    public static int squareRoot(int ){
//
//    }
}
