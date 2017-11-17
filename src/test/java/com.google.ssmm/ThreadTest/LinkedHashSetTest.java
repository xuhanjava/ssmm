package com.google.ssmm.ThreadTest;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by xuhan
 * 2017/11/3
 * 下午5:43
 */
public class LinkedHashSetTest {
    private static volatile LinkedHashSet linkedHashSet;

    public static void main(String[] args) {
        linkedHashSet = new LinkedHashSet(100);
        new Thread(()->{
            for(int i=0;i<100;i++){
                linkedHashSet.add(i);
            }
        }).start();
        List<Integer> list = new ArrayList<>();
        while(linkedHashSet.size()<100){
            list.addAll(linkedHashSet);
        }
        System.out.println(list);
    }
}
