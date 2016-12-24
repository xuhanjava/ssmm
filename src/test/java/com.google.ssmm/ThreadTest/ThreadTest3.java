package com.google.ssmm.ThreadTest;

/**
 * Created by xuhan on 16-12-21.
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        ThreadS threadS = new ThreadS();
        Thread thread1 = new Thread(threadS, "1");
        Thread thread4 = new Thread(threadS, "4");
        Thread thread2 = new Thread(threadS, "2");
        Thread thread3 = new Thread(threadS, "3");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class ThreadS extends Thread{
    @Override public void run() {
        System.out.println(this.getName());
        System.out.println("begin:"+Thread.currentThread().getName());
    }
}
