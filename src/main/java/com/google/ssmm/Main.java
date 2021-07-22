package com.google.ssmm;

import com.google.ssmm.test.AopTest;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
//        Integer value = 1;
//        System.out.println(value.hashCode());
//        value +=1;
//        System.out.println(value.hashCode());
        //System.out.println(value);
        //test2();
        //LockSupport.park();
        test4();
    }
    static void test4(){
        AopTest a = new AopTest();
        a.test4();
    }


    static int test3(){
        int x;
        try{
            x=1;
            return x;
        }catch (Exception e){
            x=2;
            return x;
        }finally {
            x=3;
        }
    }

    static void test2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1000,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 4; i++) {
            final int v = i;
            executor.submit(() -> {
                try {
                    System.out.println(v);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(123);
            });
        }

    }

    static void test1() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("await begin");
                try {
                    condition.await();
                    System.out.println("await end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("await final");
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                    System.out.println("signal begin!");
                    lock.lock();
                    condition.signal();
                    lock.unlock();
                    System.out.println("signal end!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
