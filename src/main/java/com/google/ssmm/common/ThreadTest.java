package com.google.ssmm.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xuhan on 16-12-17.
 */
public class ThreadTest {
    //static 是说这个变量,在主存中所有此类的实例用的是同一份,各个线程创建时需要从主存同一个位置拷贝到自己工作内存
    // 中去(而不是拷贝此类不同实例中的这个变量的值),
    // 也就是说只能保证线程创建时,变量的值是相同来源的,运行时还是使用各自工作内存中的值,依然会有不同步的问题.

    //“一个类可以被多个线程安全调用就是线程安全的” static是线程不安全的
    //   静态方法内的变量，每个线程调用时，都会新创建一份，不会公用一个存储单元，故不存在线程冲突的问题。
    //实例变量和static变量线程不安全的

    public static BlockingQueue<Integer> queues = new LinkedBlockingQueue<>(1);
    /*public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {

            @Override public void run() {
                System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"begin");
                try {
                    Thread.sleep(1000 * 3);
                    System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"end");
                } catch (InterruptedException e) {
                    System.out.println(123);
                }
            }
        });
        service.execute(new Runnable() {

            @Override public void run() {
                System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"begin");
                try {
                    Thread.sleep(1000 * 3);
                    System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"end");
                } catch (InterruptedException e) {
                    System.out.println(123);
                }
            }
        });

        service.shutdown();
    }*/

    public static void main(String[] args) {
        try {
            queues.put(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread1 thread1 = new Thread1();

        thread1.start();

        System.out.println("begin take");
        queues.remove();
        /*try {
            System.out.println(queues.take());
        } catch (InterruptedException e) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }*/

        /*Thread thread2 = new Thread(){
            @Override public void run() {
                System.out.println("thread2");
            }
        };*/

    }
}


class Thread1 extends Thread {
    public void run() {
        System.out.println("thread1");
        try {
            ThreadTest.queues.put(1);
            System.out.println(ThreadTest.queues.take());
        } catch (InterruptedException e) {
            yield();
            e.printStackTrace();
        }
        /*try {
            queues.add(1);
        } catch (InterruptedException e) {
            System.out.println("thread1 blocking");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                System.out.println("sleep 3s");
            }
        }*/
        System.out.println("end thread1");
    }
}
