package com.google.ssmm;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Cache2Test {
    boolean running = false;

    boolean get() {
        return running;
    }

    void doSetTrue() {
        running = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache2Test instance = new Cache2Test();
        new Thread(
                () -> {
                    while (!instance.get()) {
                        System.out.println(1);
                    }

                    System.out.println("Thread 1 finished.");
               }).start();
        Thread.sleep(100);

        //下面的线程没有使用到synchronized 所以为什么工作线程的数据没有刷新到主存，上面的线程却能够感知到
        new Thread(
                () -> {
                    instance.doSetTrue();
                    //System.out.println("Thread 2 finished.");
                    for(int i = 0;i<10000000000000l;i++){

                    }
                    int i = 3;
                }).start();
    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(8);
        map.put(10,10);
        map.put(12,10);
        map.put(11,10);
        System.out.println(map);
    }

    @Test
    public void testUuid(){
        System.out.println(UUID.randomUUID().toString().length());
    }

    @Test
    public void testTimer(){
        Timer timer = new Timer();
        timer. scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer is running");
            }
        }, 2000, 5000);
        Timer timer2 = new Timer();
    }
}