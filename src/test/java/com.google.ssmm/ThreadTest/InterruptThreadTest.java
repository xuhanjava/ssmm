package com.google.ssmm.ThreadTest;

import java.util.Date;

/**
 * Created by xuhan on 17-3-27.
 */
public class InterruptThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override public void run() {
                System.out.println("开始执行……"+new Date());
                try {
                    System.out.println(Thread.interrupted());
                    Thread.sleep(2000l);
                    System.out.println(Thread.interrupted());
                } catch (InterruptedException e) {
                    System.out.println("有这句话才会被打断"+Thread.currentThread().isInterrupted());
                }
                System.out.println("执行结束"+new Date()+"状态位"+Thread.currentThread().isInterrupted());
            }
        };
        /*try {
            thread.join();
            thread.start();
        } catch (InterruptedException e) {
            System.out.println("等待join");
        }
            thread.interrupt();
        System.out.println("main is end"+new Date());*/
        try {
            thread.join();
            thread.interrupt();
            System.out.println(Thread.interrupted());
        } catch (InterruptedException e) {
            System.out.println("join exception");
        }
        //thread.start();
    }
}
