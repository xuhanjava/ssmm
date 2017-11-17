package com.google.ssmm.ThreadTest;

import com.google.common.collect.Lists;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuhan
 * 2017/11/3
 * 下午1:53
 */
public class ConcurrentLinkedQueueTest1 {

    static volatile ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        concurrentLinkedQueue.addAll(Lists.newArrayList(1,2,3));
        CountDownLatch cdl = new CountDownLatch(1);
        new Thread(()->{
            for(int i = 0;i<=100;i++){
                concurrentLinkedQueue.addAll(Lists.newArrayList(1,2,3));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cdl.countDown();
        }).start();
       int i = 0;
        while(cdl.getCount()>0){
            Integer poll = concurrentLinkedQueue.poll();
            if(poll != null){
                System.out.println(poll);
                try {
                    Thread.sleep(3l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }

        }
        try {
            cdl.await();
            System.out.println("final size="+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
